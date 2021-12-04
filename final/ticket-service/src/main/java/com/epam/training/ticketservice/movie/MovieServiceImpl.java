package com.epam.training.ticketservice.movie;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepo movieRepo;

    public MovieServiceImpl(MovieRepo movieRepo) { this.movieRepo = movieRepo; }

    @Override
    public Movie getMovieByMovieTitle(String movieTitle) {
        return movieRepo.searchMovieByMovieTitle(movieTitle);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public void createMovie(String movieTitle, String movieGenre, Integer moviePlayTime) {
        movieRepo.save(Movie.builder()
                .withMovieTitle(movieTitle)
                .withMovieGenre(movieGenre)
                .withMoviePlayTime(moviePlayTime)
                .build());
    }

    @Override
    public void deleteMovie(String movieTitle) {
        if (getMovieByMovieTitle(movieTitle) != null)
            movieRepo.delete(getMovieByMovieTitle(movieTitle));
    }

    @Override
    public void updateMovie(String movieTitle, String movieGenre, Integer moviePlayTime) {
        Movie movieToUpdate = getMovieByMovieTitle(movieTitle);
        if (movieToUpdate != null) {
            movieToUpdate.setMovieTitle(movieTitle);
            movieToUpdate.setMovieGenre(movieGenre);
            movieToUpdate.setMoviePlayTime(moviePlayTime);
            movieRepo.save(movieToUpdate);
        }
    }
}
