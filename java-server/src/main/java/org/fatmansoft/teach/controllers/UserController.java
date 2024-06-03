package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.EUserType;
import org.fatmansoft.teach.models.User;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.repository.UserRepository;
import org.fatmansoft.teach.repository.UserTypeRepository;
import org.fatmansoft.teach.service.UserService;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public DataResponse registerUser(@Valid @RequestBody DataRequest dataRequest) {
        String userName = dataRequest.getString("username");
        String passWord = dataRequest.getString("password");

        Optional<User> existingUser = userRepository.findByUsername(userName);
        if (existingUser.isPresent()) {
            return CommonMethod.getReturnMessageError("用户名已存在,请更换用户名");
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(passWord);
        user.setUserType(userTypeRepository.findByName(EUserType.ROLE_ADMIN));
        userRepository.save(user);
        return CommonMethod.getReturnMessageOK();
    }@PostMapping("/loginUser")
    public DataResponse loginUser(@Valid @RequestBody DataRequest dataRequest) {
        String userName = dataRequest.getString("username");
        String passWord = dataRequest.getString("password");

        Optional<User> existingUser = userRepository.findByUsername(userName);
        if (!existingUser.isPresent()) {
            return CommonMethod.getReturnMessageError("用户名不存在");
        }
        String passWordDB=existingUser.get().getPassword();
        if(!passWord.equals(passWordDB)){
            return CommonMethod.getReturnMessageError("密码错误");
        }
        return CommonMethod.getReturnMessageOK();
    }

}
