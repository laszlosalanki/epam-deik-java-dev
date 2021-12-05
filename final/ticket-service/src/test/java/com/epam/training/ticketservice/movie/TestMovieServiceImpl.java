package com.epam.training.ticketservice.movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class TestMovieServiceImpl {

    private MovieServiceImpl underTest;

    @Mock
    private MovieRepo movieRepo;

    @BeforeEach
    public void setUnderTest() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieServiceImpl(movieRepo);
    }

    @Test
    public void testGetMovieByMovieTitleShouldReturnAMovieWithAGivenMatchingName() {
        //Given
        final Movie expected = new Movie("name","genre",10);
        BDDMockito.given(movieRepo.searchMovieByMovieTitle("name")).willReturn(expected);

        //When
        final Movie result = underTest.getMovieByMovieTitle("name");

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testGetAllMovieShouldReturnAListOfMovies() {
        //Given
        final List<Movie> expected = List.of(new Movie("name","genre",10));
        BDDMockito.given(movieRepo.findAll()).willReturn(expected);

        //When
        final List<Movie> result = underTest.getAllMovies();

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testCreateMovieShouldCreateMovieWhenGivenMovieArguments() {
        //Given
        final Movie movie = new Movie("name","genre",10);

        //When
        underTest.createMovie("name","genre",10);

        //Then
        Mockito.verify(movieRepo).save(movie);
    }

    @Test
    public void testDeleteMovieShouldDeleteMovieWhenGivenMatchingMovieName() {
        //Given
        final Movie movie = new Movie("name","genre",10);
        BDDMockito.given(movieRepo.searchMovieByMovieTitle("name")).willReturn(movie);

        //When
        underTest.deleteMovie("name");

        //Then
        Mockito.verify(movieRepo).delete(movie);
    }

    @Test
    public void testUpdateMovieShouldUpdateMovieWhenGivenCorrectMovieDetails() {
        //Given
        final Movie movie = new Movie("name","genre",10);
        BDDMockito.given(movieRepo.searchMovieByMovieTitle("name")).willReturn(movie);

        //When
        underTest.updateMovie("name","newGenre",20);

        //Then
        Mockito.verify(movieRepo).save(movie);
    }
}
