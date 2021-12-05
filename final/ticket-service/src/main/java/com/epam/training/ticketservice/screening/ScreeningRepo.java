package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepo extends JpaRepository<Screening, Long> {
    List<Screening> findAll();
    Screening getScreeningByMovieOfScreeningAndRoomOfScreeningAndTimeOfScreening(Movie movieOfScreening, Room roomOfScreening, LocalDateTime timeOfScreening);
}
