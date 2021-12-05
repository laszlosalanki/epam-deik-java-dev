package com.epam.training.ticketservice.helpers;

import com.epam.training.ticketservice.configuration.Constants;
import com.epam.training.ticketservice.room.Room;
import com.epam.training.ticketservice.screening.Screening;
import com.epam.training.ticketservice.screening.ScreeningRepo;

import java.time.LocalDateTime;

public class OverlappingChecker {
    public static boolean isScreeningOverlapsWithAnOtherScreening(ScreeningRepo screeningRepo, Room roomOfScreening, LocalDateTime timeOfScreening) {
        for (Screening s : screeningRepo.findAll()) {
            if (s.getRoomOfScreening().equals(roomOfScreening)) {
                LocalDateTime movieStart = s.getTimeOfScreening();
                LocalDateTime movieEnd = movieStart.plusMinutes(s.getMovieOfScreening().getMoviePlayTime());
                if (timeOfScreening.isBefore(movieEnd) && timeOfScreening.isAfter(movieStart))
                    return true;
            }
        }

        return false;
    }

    public static boolean isScreeningOverlapsWithBreakTime(ScreeningRepo screeningRepo, Room roomOfScreening, LocalDateTime timeOfScreening) {
        for (Screening s : screeningRepo.findAll()) {
            if (s.getRoomOfScreening().equals(roomOfScreening)) {
                LocalDateTime movieStart = s.getTimeOfScreening();
                movieStart = movieStart.plusMinutes(s.getMovieOfScreening().getMoviePlayTime());
                LocalDateTime movieEnd = movieStart.plusMinutes(Constants.BREAK_LENGTH);
                if (timeOfScreening.isBefore(movieEnd) && timeOfScreening.isAfter(movieStart))
                    return true;
            }
        }

        return false;
    }
}
