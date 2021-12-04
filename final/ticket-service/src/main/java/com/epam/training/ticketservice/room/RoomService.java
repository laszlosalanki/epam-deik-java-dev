package com.epam.training.ticketservice.room;

import java.util.List;

public interface RoomService {
    Room getRoomByRoomName(String roomName);
    List<Room> getAllRooms();
    void createRoom(String roomName, Long roomNumOfRows, Long roomNumOfCols);
    void deleteRoom(String roomName);
    void updateRoom(String roomName, Long roomNumOfRows, Long roomNumOfCols);
}
