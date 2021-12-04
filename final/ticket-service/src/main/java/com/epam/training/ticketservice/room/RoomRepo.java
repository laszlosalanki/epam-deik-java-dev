package com.epam.training.ticketservice.room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
    Room searchRoomByRoomName(String roomName);
}
