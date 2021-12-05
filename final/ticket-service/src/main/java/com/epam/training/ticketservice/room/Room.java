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

    public Room() {

    }

    public Room(String roomName, Long roomNumOfRows, Long roomNumOfCols) {
        this.roomName = roomName;
        this.roomNumOfRows = roomNumOfRows;
        this.roomNumOfCols = roomNumOfCols;
    }

    public Room(final RoomBuilder roomBuilder) {
        this.roomName = roomBuilder.roomName;
        this.roomNumOfRows = roomBuilder.roomNumOfRows;
        this.roomNumOfCols = roomBuilder.roomNumOfCols;
    }

    public static RoomBuilder builder() {
        return new RoomBuilder();
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomNumOfRows(Long roomNumOfRows) {
        this.roomNumOfRows = roomNumOfRows;
    }

    public void setRoomNumOfCols(Long roomNumOfCols) {
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
        return new StringBuilder("Room ").append(roomName).append(" with ").append(roomNumOfCols * roomNumOfRows)
            .append(" seats, ").append(roomNumOfRows).append(" rows and ").append(roomNumOfCols)
            .append(" columns").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(roomName, room.roomName)
                && Objects.equals(roomNumOfRows, room.roomNumOfRows)
                && Objects.equals(roomNumOfCols, room.roomNumOfCols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, roomNumOfRows, roomNumOfCols);
    }

    public static final class RoomBuilder {
        private String roomName;
        private Long roomNumOfRows;
        private Long roomNumOfCols;

        private RoomBuilder() {

        }

        public RoomBuilder withRoomName(String roomName) {
            this.roomName = roomName;
            return this;
        }

        public RoomBuilder withRoomNumOfRows(Long roomNumOfRows) {
            this.roomNumOfRows = roomNumOfRows;
            return this;
        }

        public RoomBuilder withRoomNumOfCols(Long roomNumOfCols) {
            this.roomNumOfCols = roomNumOfCols;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
