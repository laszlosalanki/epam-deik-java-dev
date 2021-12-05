package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

public class TestScreeningServiceImpl {

    private final Movie movie = new Movie("name","genre",100);
    private final Room room = new Room("name",10L,10L);
    private final LocalDateTime date = LocalDateTime.of(2021,10,10,10,10);
    private final Screening screening = new Screening(movie,room,date);

    private ScreeningServiceImpl underTest;

    @Mock
    private ScreeningRepo screeningRepo;

    @BeforeEach
    public void setUnderTest() {
        MockitoAnnotations.openMocks(this);
        underTest = new ScreeningServiceImpl(screeningRepo);
    }

    @Test
    public void testGetAllScreeningsShouldReturnAListOfScreening() {
        //Given
        final List<Screening> expected = List.of(screening);
        BDDMockito.given(screeningRepo.findAll()).willReturn(expected);

        //When
        final List<Screening> result = underTest.getAllScreenings();

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testCreateScreeningShouldCreateScreeningWhenGivenNonOverlappingDate() {
        //Given
        final LocalDateTime newDate = LocalDateTime.of(2021,12,12,10,10);
        final Screening screening = new Screening(movie,room,newDate);
        BDDMockito.given(screeningRepo.findAll()).willReturn(List.of(screening));

        //When
        underTest.createScreening(movie,room,LocalDateTime.of(2021,12,12,10,10));

        //Then
        Mockito.verify(screeningRepo).save(screening);
    }

    @Test
    public void testCreateScreeningShouldNotCreateScreeningWhenGivenOverlappingDate() {
        //Given
        BDDMockito.given(screeningRepo.findAll()).willReturn(List.of(screening));
        final String expected = "There is an overlapping screening";

        //When
        final String result = underTest.createScreening(movie,room,date.plusMinutes(5));

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testCreateScreeningShouldNotCreateScreeningWhenGivenOverlappingDateInsideBreakTime() {
        //Given
        BDDMockito.given(screeningRepo.findAll()).willReturn(List.of(screening));
        final String expected = "This would start in the break period after another screening in this room";

        //When
        final String result = underTest.createScreening(movie,room,date.plusMinutes(movie.getMoviePlayTime()).plusMinutes(5));

        //Then
        Assertions.assertEquals(expected,result);
    }

}
