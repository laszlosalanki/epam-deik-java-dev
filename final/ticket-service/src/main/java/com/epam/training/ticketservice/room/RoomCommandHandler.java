package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.account.Account;
import com.epam.training.ticketservice.account.AccountService;
import com.epam.training.ticketservice.data.Constants;
import com.epam.training.ticketservice.data.Role;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@ShellComponent
public class RoomCommandHandler {

    RoomService roomService;
    AccountService accountService;

    public RoomCommandHandler(RoomService roomService, AccountService accountService) {
        this.roomService = roomService;
        this.accountService = accountService;
    }

    @ShellMethodAvailability("accountAdmin")
    @ShellMethod(value = Constants.CREATE_ROOM_METHOD_VALUE, key = Constants.CREATE_ROOM_COMMAND)
    public void createRoom(String roomName, Long roomNumOfRows, Long roomNumOfCols) {
        roomService.createRoom(roomName, roomNumOfRows, roomNumOfCols);
    }

    @ShellMethodAvailability("accountAdmin")
    @ShellMethod(value = Constants.UPDATE_ROOM_METHOD_VALUE, key = Constants.UPDATE_ROOM_COMMAND)
    public void updateRoom(String roomName, Long roomNumOfRows, Long roomNumOfCols) {
        roomService.updateRoom(roomName, roomNumOfRows, roomNumOfCols);
    }

    @ShellMethodAvailability("accountAdmin")
    @ShellMethod(value = Constants.DELETE_ROOM_METHOD_VALUE, key = Constants.DELETE_ROOM_COMMAND)
    public void deleteRoom(String roomName) {
        roomService.deleteRoom(roomName);
    }

    @ShellMethod(value = Constants.LIST_ROOMS_METHOD_VALUE, key = Constants.LIST_ROOMS_COMMAND)
    public String listRooms() {
        List<Room> roomList = roomService.getAllRooms();
        if (roomList.size() == 0) {
            return Constants.NO_ROOMS_AVAILABLE;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Room r : roomList) {
            stringBuilder.append(r.toString()).append('\n');
        }
        return stringBuilder.toString();
    }

    private Availability accountAdmin() {
        Account account = accountService.getActualAccount();
        if (account != null) {
            if (account.getAccountRole().equals(Role.ADMIN)) {
                return Availability.available();
            }
        }
        return Availability.unavailable(Constants.UNAVAILABLE_COMMAND);
    }
}
