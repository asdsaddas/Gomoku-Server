package org.fatmansoft.teach.service;

import org.fatmansoft.teach.models.Room;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoomService {
    public Map getMapFromRoom(Room r) {
        Map m = new HashMap();
        if(r == null)
            return m;
        m.put("roomId",r.getRoomId());
        m.put("hostname",r.getHostname());
        m.put("username1",r.getUsername1());
        m.put("username2",r.getUsername2());
        m.put("content",r.getContent());
        return m;
    }

}
