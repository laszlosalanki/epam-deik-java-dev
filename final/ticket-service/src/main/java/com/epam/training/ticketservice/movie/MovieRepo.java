package com.epam.training.ticketservice.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    Movie searchMovieByMovieTitle(String movieTitle);
}
