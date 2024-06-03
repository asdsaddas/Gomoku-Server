package org.fatmansoft.teach.service;

import org.fatmansoft.teach.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    public Map getMapFromUser(User u) {
        Map m = new HashMap();
        if(u == null)
            return m;
        m.put("username",u.getUserName());
        m.put("password",u.getPassword());
        return m;
    }

}
