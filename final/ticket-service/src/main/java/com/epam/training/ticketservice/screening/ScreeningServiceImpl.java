package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.data.Constants;
import com.epam.training.ticketservice.helpers.OverlappingChecker;
import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private ScreeningRepo screeningRepo;

    public ScreeningServiceImpl(ScreeningRepo screeningRepo) {
        this.screeningRepo = screeningRepo;
    }

    @Override
    public List<Screening> getAllScreenings() {
        return screeningRepo.findAll();
    }

    @Override
    public String createScreening(Movie movieOfScreening, Room roomOfScreening, LocalDateTime timeOfScreening) {
        if (OverlappingChecker.isScreeningOverlapsWithAnOtherScreening(screeningRepo, roomOfScreening, timeOfScreening))
            return Constants.OVERLAPPING_SCREENING;

        if (OverlappingChecker.isScreeningOverlapsWithBreakTime(screeningRepo, roomOfScreening, timeOfScreening))
            return Constants.OVERLAPPING_BREAK;

        if (movieOfScreening != null && roomOfScreening != null && timeOfScreening != null)
            screeningRepo.save(Screening.builder()
                            .withMovieOfScreening(movieOfScreening)
                            .withRoomOfScreening(roomOfScreening)
                            .withTimeOfScreening(timeOfScreening)
                            .build());

        return null;
    }

    @Override
    public void deleteScreening(Movie movieOfScreening, Room roomOfScreening, LocalDateTime timeOfScreening) {
        if (getScreening(movieOfScreening, roomOfScreening, timeOfScreening) != null)
            screeningRepo.delete(getScreening(movieOfScreening, roomOfScreening, timeOfScreening));
    }

    private Screening getScreening(Movie movieOfScreening, Room roomOfScreening, LocalDateTime timeOfScreening) {
        return screeningRepo.getScreeningByMovieOfScreeningAndRoomOfScreeningAndTimeOfScreening(movieOfScreening, roomOfScreening, timeOfScreening);
    }
}
