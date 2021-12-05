package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningService {
    List<Screening> getAllScreenings();
    String createScreening(Movie movieOfScreening, Room roomOfScreening, LocalDateTime timeOfScreening);
    void deleteScreening(Movie movieOfScreening, Room roomOfScreening, LocalDateTime timeOfScreening);
}
