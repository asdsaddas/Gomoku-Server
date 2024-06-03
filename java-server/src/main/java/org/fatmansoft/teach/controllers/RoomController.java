package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.Room;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.repository.RoomRepository;
import org.fatmansoft.teach.service.RoomService;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;
    @PostMapping("/getRoomData")
    public DataResponse getRoomData(@Valid @RequestBody DataRequest dataRequest) {
        Integer roomId = dataRequest.getInteger("roomId");
        List dataList = new ArrayList();
        Room result=roomRepository.getById(roomId);
        dataList.add(roomService.getMapFromRoom(result));
        return CommonMethod.getReturnData(dataList,null);
    }
    @PostMapping("/editContent")
    public DataResponse editContent(@Valid @RequestBody DataRequest dataRequest) {
        Integer roomId = dataRequest.getInteger("roomId");
        String content= dataRequest.getString("content");
        Optional<Room> op=roomRepository.findById(roomId);
        Room room=op.get();
        room.setContent(content);
        roomRepository.save(room);
        return CommonMethod.getReturnMessageOK();
    }
}
