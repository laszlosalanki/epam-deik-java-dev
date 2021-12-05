package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.data.Constants;
import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Screening() {

    }

    public Screening(Movie movieOfScreening, Room roomOfScreening, LocalDateTime timeOfScreening) {
        this.movieOfScreening = movieOfScreening;
        this.roomOfScreening = roomOfScreening;
        this.timeOfScreening = timeOfScreening;
    }

    public Screening(final ScreeningBuilder screeningBuilder) {
        this.movieOfScreening = screeningBuilder.movieOfScreening;
        this.roomOfScreening = screeningBuilder.roomOfScreening;
        this.timeOfScreening = screeningBuilder.timeOfScreening;
    }

    public static ScreeningBuilder builder() {
        return new ScreeningBuilder();
    }

    public void setMovieOfScreening(Movie movieOfScreening) {
        this.movieOfScreening = movieOfScreening;
    }

    public void setRoomOfScreening(Room roomOfScreening) {
        this.roomOfScreening = roomOfScreening;
    }

    public void setTimeOfScreening(LocalDateTime timeOfScreening) {
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
        return new StringBuilder(movieOfScreening.getMovieTitle())
                .append(" (")
                .append(movieOfScreening.getMovieGenre())
                .append(", ")
                .append(movieOfScreening.getMoviePlayTime())
                .append(" minutes), screened in room ")
                .append(roomOfScreening.getRoomName())
                .append(", at ")
                .append(timeOfScreening.format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN)))
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Screening screening = (Screening) o;
        return Objects.equals(movieOfScreening, screening.movieOfScreening)
                && Objects.equals(roomOfScreening, screening.roomOfScreening)
                && Objects.equals(timeOfScreening, screening.timeOfScreening);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieOfScreening, roomOfScreening, timeOfScreening);
    }

    public static final class ScreeningBuilder {
        private Movie movieOfScreening;
        private Room roomOfScreening;
        private LocalDateTime timeOfScreening;

        private ScreeningBuilder() {

        }

        public ScreeningBuilder withMovieOfScreening(Movie movieOfScreening) {
            this.movieOfScreening = movieOfScreening;
            return this;
        }

        public ScreeningBuilder withRoomOfScreening(Room roomOfScreening) {
            this.roomOfScreening = roomOfScreening;
            return this;
        }

        public ScreeningBuilder withTimeOfScreening(LocalDateTime timeOfScreening) {
            this.timeOfScreening = timeOfScreening;
            return this;
        }

        public Screening build() {
            return new Screening(this);
        }
    }
}
