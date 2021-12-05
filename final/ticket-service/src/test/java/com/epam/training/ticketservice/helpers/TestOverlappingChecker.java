package com.epam.training.ticketservice.helpers;

import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;
import com.epam.training.ticketservice.screening.Screening;
import com.epam.training.ticketservice.screening.ScreeningRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

public class TestOverlappingChecker {

    private final Movie movie = new Movie("name","genre",100);
    private final Room room = new Room("name",10L,10L);
    private final LocalDateTime date = LocalDateTime.of(2021,10,10,10,10);
    private final Screening screening = new Screening(movie,room,date);


    @Mock
    private ScreeningRepo screeningRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsScreeningOverLapsWithAnOtherScreeningShouldReturnTrueIfGivenOverlappingScreenings() {
        //Given
        BDDMockito.given(screeningRepo.findAll()).willReturn(List.of(screening));

        //When
        final boolean result = OverlappingChecker.isScreeningOverlapsWithAnOtherScreening(screeningRepo,room,date.plusMinutes(5));

        //Then
        Assertions.assertTrue(result);
    }
    @Test
    public void testIsScreeningOverLapsWithAnOtherScreeningShouldReturnFalseIfGivenNonOverlappingScreenings() {
        //Given
        BDDMockito.given(screeningRepo.findAll()).willReturn(List.of(screening));

        //When
        final boolean result = OverlappingChecker.isScreeningOverlapsWithAnOtherScreening(screeningRepo,room,date.plusYears(1));

        //Then
        Assertions.assertFalse(result);
    }
    @Test
    public void testIsScreeningOverLapsWithBreakTimeShouldReturnFalseIfTheGivenScreeningWontBeScreenedInsideBreakTime() {
        //Given
        BDDMockito.given(screeningRepo.findAll()).willReturn(List.of(screening));

        //When
        final boolean result = OverlappingChecker.isScreeningOverlapsWithAnOtherScreening(screeningRepo,room,
                date.plusMinutes(movie.getMoviePlayTime()).plusMinutes(15));

        //Then
        Assertions.assertFalse(result);
    }
    @Test
    public void testIsScreeningOverLapsWithBreakTimeShouldReturnTrueIfTheGivenScreeningWillBeScreenedInsideBreakTime() {
        //Given
        BDDMockito.given(screeningRepo.findAll()).willReturn(List.of(screening));

        //When
        final boolean result = OverlappingChecker.isScreeningOverlapsWithAnOtherScreening(screeningRepo,room,
                date.plusMinutes(movie.getMoviePlayTime()).plusMinutes(5));

        //Then
        Assertions.assertFalse(result);
    }

}
