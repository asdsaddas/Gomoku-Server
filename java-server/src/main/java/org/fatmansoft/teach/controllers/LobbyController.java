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
@RequestMapping("/auth/lobby")
public class LobbyController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;


    @PostMapping("/createRoom")
    public DataResponse createRoom(@Valid @RequestBody DataRequest dataRequest) {
        String hostName = dataRequest.getString("hostname");
        String userName1 = dataRequest.getString("username1");
        String content= dataRequest.getString("content");
        Room room = new Room();
        room.setHostname(hostName);
        room.setUsername1(userName1);
        room.setContent(content);
        roomRepository.save(room);
        return CommonMethod.getReturnMessageOK();
    }
    @PostMapping("/getRoomList")
    public DataResponse getRoomList(@Valid @RequestBody DataRequest dataRequest) {
        List dataList = new ArrayList();
        List<Room> rList=roomRepository.findRoomList();
        for (int i = 0; i < rList.size(); i++) {
            dataList.add(roomService.getMapFromRoom(rList.get(i)));
        }
        return CommonMethod.getReturnData(dataList,null);
    }
    @PostMapping("/deleteRoom")
    public DataResponse deleteRoom(@Valid @RequestBody DataRequest dataRequest) {
        Integer roomId = dataRequest.getInteger("roomId");
        Optional<Room> op=roomRepository.findById(roomId);
        Room room=op.get();
        roomRepository.delete(room);
        return CommonMethod.getReturnMessageOK();
    }
    @PostMapping("/joinRoom")
    public DataResponse joinRoom(@Valid @RequestBody DataRequest dataRequest) {
        Integer roomId = dataRequest.getInteger("roomId");
        String username2= dataRequest.getString("username2");
        Optional<Room> op=roomRepository.findById(roomId);
        Room room=op.get();
        room.setUsername2(username2);
        roomRepository.save(room);
        return CommonMethod.getReturnMessageOK();
    }

}
