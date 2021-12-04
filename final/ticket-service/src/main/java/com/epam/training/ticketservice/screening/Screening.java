package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Screening {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Movie movieOfScreening;

    @ManyToOne
    private Room roomOfScreening;

    private LocalDateTime timeOfScreening;

    public Screening() {}

    public Screening(Movie movieOfScreening, Room roomOfScreening, LocalDateTime timeOfScreening) {
        this.movieOfScreening = movieOfScreening;
        this.roomOfScreening = roomOfScreening;
        this.timeOfScreening = timeOfScreening;
    }

    public Movie getMovieOfScreening() {
        return movieOfScreening;
    }

    public Room getRoomOfScreening() {
        return roomOfScreening;
    }

    public LocalDateTime getTimeOfScreening() {
        return timeOfScreening;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "movieOfScreening=" + movieOfScreening +
                ", roomOfScreening=" + roomOfScreening +
                ", timeOfScreening=" + timeOfScreening +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return Objects.equals(movieOfScreening, screening.movieOfScreening) && Objects.equals(roomOfScreening, screening.roomOfScreening) && Objects.equals(timeOfScreening, screening.timeOfScreening);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieOfScreening, roomOfScreening, timeOfScreening);
    }
}
