package com.epam.training.ticketservice.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String roomName;

    private Long roomNumOfRows;
    private Long roomNumOfCols;

    public Room() {}

    public Room(String roomName, Long roomNumOfRows, Long roomNumOfCols) {
        this.roomName = roomName;
        this.roomNumOfRows = roomNumOfRows;
        this.roomNumOfCols = roomNumOfCols;
    }

    public String getRoomName() {
        return roomName;
    }

    public Long getRoomNumOfRows() {
        return roomNumOfRows;
    }

    public Long getRoomNumOfCols() {
        return roomNumOfCols;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomName='" + roomName + '\'' +
                ", roomNumOfRows=" + roomNumOfRows +
                ", roomNumOfCols=" + roomNumOfCols +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomName, room.roomName) && Objects.equals(roomNumOfRows, room.roomNumOfRows) && Objects.equals(roomNumOfCols, room.roomNumOfCols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, roomNumOfRows, roomNumOfCols);
    }
}
