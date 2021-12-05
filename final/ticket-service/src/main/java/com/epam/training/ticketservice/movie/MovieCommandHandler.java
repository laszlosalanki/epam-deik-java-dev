package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.account.Account;
import com.epam.training.ticketservice.account.AccountService;
import com.epam.training.ticketservice.data.Constants;
import com.epam.training.ticketservice.data.Role;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@ShellComponent
public class MovieCommandHandler {

    private MovieService movieService;
    private AccountService accountService;

    public MovieCommandHandler(MovieService movieService, AccountService accountService) {
        this.movieService = movieService;
        this.accountService = accountService;
    }

    @ShellMethodAvailability("accountAdmin")
    @ShellMethod(value = Constants.CREATE_MOVIE_METHOD_VALUE, key = Constants.CREATE_MOVIE_COMMAND)
    public void createMovie(String movieTitle, String movieGenre, Integer moviePlayTime) {
        movieService.createMovie(movieTitle, movieGenre, moviePlayTime);
    }

    @ShellMethodAvailability("accountAdmin")
    @ShellMethod(value = Constants.UPDATE_MOVIE_METHOD_VALUE, key = Constants.UPDATE_MOVIE_COMMAND)
    public void updateMovie(String movieTitle, String movieGenre, Integer moviePlayTime) {
        movieService.updateMovie(movieTitle, movieGenre, moviePlayTime);
    }

    @ShellMethodAvailability("accountAdmin")
    @ShellMethod(value = Constants.DELETE_MOVIE_METHOD_VALUE, key = Constants.DELETE_MOVIE_COMMAND)
    public void deleteMovie(String movieTitle) {
        movieService.deleteMovie(movieTitle);
    }

    @ShellMethod(value = Constants.LIST_MOVIES_METHOD_VALUE, key = Constants.LIST_MOVIES_COMMAND)
    public String listMovies() {
        List<Movie> movieList = movieService.getAllMovies();
        if (movieList.size() == 0)
            return Constants.NO_MOVIES_AVAILABLE;

        StringBuilder stringBuilder = new StringBuilder();
        for (Movie m : movieList)
            stringBuilder.append(m.toString()).append('\n');
        return stringBuilder.toString();
    }

    private Availability accountAdmin() {
        Account account = accountService.getActualAccount();
        if (account != null)
            if (account.getAccountRole().equals(Role.ADMIN))
                return Availability.available();
        return Availability.unavailable(Constants.UNAVAILABLE_COMMAND);
    }
}
