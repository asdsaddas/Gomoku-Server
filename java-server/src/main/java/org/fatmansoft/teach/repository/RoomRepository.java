package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer>{
    @Query(value = "from Room")
    List<Room> findRoomList();

}
