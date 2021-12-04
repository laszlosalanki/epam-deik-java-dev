package com.epam.training.ticketservice.room;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepo roomRepo;

    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public Room getRoomByRoomName(String roomName) {
        return roomRepo.searchRoomByRoomName(roomName);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public void createRoom(String roomName, Long roomNumOfRows, Long roomNumOfCols) {
        roomRepo.save(Room.builder()
                .withRoomName(roomName)
                .withRoomNumOfRows(roomNumOfRows)
                .withRoomNumOfCols(roomNumOfCols)
                .build());
    }

    @Override
    public void deleteRoom(String roomName) {
        if (getRoomByRoomName(roomName) != null)
            roomRepo.delete(getRoomByRoomName(roomName));
    }

    @Override
    public void updateRoom(String roomName, Long roomNumOfRows, Long roomNumOfCols) {
        Room roomToUpdate = getRoomByRoomName(roomName);
        if (roomToUpdate != null) {
            roomToUpdate.setRoomName(roomName);
            roomToUpdate.setRoomNumOfRows(roomNumOfRows);
            roomToUpdate.setRoomNumOfCols(roomNumOfCols);
            roomRepo.save(roomToUpdate);
        }
    }
}
