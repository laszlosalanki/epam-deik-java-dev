package com.epam.training.ticketservice.movie;

import java.util.List;

public interface MovieService {
    Movie getMovieByMovieTitle(String movieTitle);
    List<Movie> getAllMovies();
    void createMovie(String movieTitle, String movieGenre, Integer moviePlayTime);
    void deleteMovie(String movieTitle);
    void updateMovie(String movieTitle, String movieGenre, Integer moviePlayTime);
}
