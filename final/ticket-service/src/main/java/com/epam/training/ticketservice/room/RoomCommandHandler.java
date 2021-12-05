package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.data.Constants;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class RoomCommandHandler {

    RoomService roomService;

    public RoomCommandHandler(RoomService roomService) {
        this.roomService = roomService;
    }

    @ShellMethod(value = Constants.CREATE_ROOM_METHOD_VALUE, key = Constants.CREATE_ROOM_COMMAND)
    public void createRoom(String roomName, Long roomNumOfRows, Long roomNumOfCols) {
        roomService.createRoom(roomName, roomNumOfRows, roomNumOfCols);
    }

    @ShellMethod(value = Constants.UPDATE_ROOM_METHOD_VALUE, key = Constants.UPDATE_ROOM_COMMAND)
    public void updateRoom(String roomName, Long roomNumOfRows, Long roomNumOfCols) {
        roomService.updateRoom(roomName, roomNumOfRows, roomNumOfCols);
    }

    @ShellMethod(value = Constants.DELETE_ROOM_METHOD_VALUE, key = Constants.DELETE_ROOM_COMMAND)
    public void deleteRoom(String roomName) {
        roomService.deleteRoom(roomName);
    }

    @ShellMethod(value = Constants.LIST_ROOMS_METHOD_VALUE, key = Constants.LIST_ROOMS_COMMAND)
    public String listRooms() {
        List<Room> roomList = roomService.getAllRooms();
        if (roomList.size() == 0)
            return Constants.NO_ROOMS_AVAILABLE;

        StringBuilder stringBuilder = new StringBuilder();
        for (Room r : roomList)
            stringBuilder.append(r.toString()).append('\n');
        return stringBuilder.toString();
    }
}
