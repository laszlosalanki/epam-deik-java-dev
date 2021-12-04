package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.configuration.Constants;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class MovieCommandHandler {

    private MovieService movieService;

    public MovieCommandHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    @ShellMethod(value = Constants.CREATE_MOVIE_METHOD_VALUE, key = Constants.CREATE_MOVIE_COMMAND)
    public void createMovie(String movieTitle, String movieGenre, Integer moviePlayTime) {
        movieService.createMovie(movieTitle, movieGenre, moviePlayTime);
    }

    @ShellMethod(value = Constants.UPDATE_MOVIE_METHOD_VALUE, key = Constants.UPDATE_MOVIE_COMMAND)
    public void updateMovie(String movieTitle, String movieGenre, Integer moviePlayTime) {
        movieService.updateMovie(movieTitle, movieGenre, moviePlayTime);
    }

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
}
