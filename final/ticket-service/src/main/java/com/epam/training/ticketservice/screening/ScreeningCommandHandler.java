package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.data.Constants;
import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.movie.MovieService;
import com.epam.training.ticketservice.room.Room;
import com.epam.training.ticketservice.room.RoomService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ShellComponent
public class ScreeningCommandHandler {

    ScreeningService screeningService;
    MovieService movieService;
    RoomService roomService;

    public ScreeningCommandHandler(ScreeningService screeningService, MovieService movieService, RoomService roomService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
        this.roomService = roomService;
    }

    @ShellMethod(value = Constants.CREATE_SCREENING_METHOD_VALUE, key = Constants.CREATE_SCREENING_COMMAND)
    public String createScreening(String movieTitleOfScreening, String roomNameOfScreening, String timeOfScreeningAsString) {
        Movie movie = movieService.getMovieByMovieTitle(movieTitleOfScreening);
        Room room = roomService.getRoomByRoomName(roomNameOfScreening);

        LocalDateTime localDateTime = LocalDateTime.parse(timeOfScreeningAsString, DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN));
        return screeningService.createScreening(movie, room, localDateTime);
    }

    @ShellMethod(value = Constants.LIST_SCREENINGS_METHOD_VALUE, key = Constants.LIST_SCREENINGS_COMMAND)
    public String listScreenings() {
        List<Screening> screeningList = screeningService.getAllScreenings();
        if (screeningList.size() == 0)
            return Constants.NO_SCREENINGS_AVAILABLE;

        StringBuilder stringBuilder = new StringBuilder();
        for (Screening s : screeningList)
            stringBuilder.append(s.toString()).append('\n');
        return stringBuilder.toString();
    }

    @ShellMethod(value = Constants.DELETE_SCREENING_METHOD_VALUE, key = Constants.DELETE_SCREENING_COMMAND)
    public void deleteScreening(String movieTitleOfScreening, String roomNameOfScreening, String timeOfScreeningAsString) {
        Movie movie = movieService.getMovieByMovieTitle(movieTitleOfScreening);
        Room room = roomService.getRoomByRoomName(roomNameOfScreening);
        LocalDateTime dateTime = LocalDateTime.parse(timeOfScreeningAsString, DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN));
        screeningService.deleteScreening(movie, room, dateTime);
    }
}
