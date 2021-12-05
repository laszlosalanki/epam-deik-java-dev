package com.epam.training.ticketservice.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class TestRoomServiceImpl {

    private RoomServiceImpl underTest;

    @Mock
    private RoomRepo roomRepo;

    @BeforeEach
    public void setUnderTest() {
        MockitoAnnotations.openMocks(this);
        underTest = new RoomServiceImpl(roomRepo);
    }

    @Test
    public void testGetRoomByRoomNameShouldReturnARoomWithAGivenMatchingName() {
        //Given
        final Room expected = new Room("name",10L,10L);
        BDDMockito.given(roomRepo.searchRoomByRoomName("name")).willReturn(expected);

        //When
        final Room result = underTest.getRoomByRoomName("name");

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testGetAllRoomsShouldReturnAListOfRooms() {
        //Given
        final List<Room> expected = List.of(new Room("name",10L,10L));
        BDDMockito.given(roomRepo.findAll()).willReturn(expected);

        //When
        final List<Room> result = underTest.getAllRooms();

        //Then
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testCreateRoomShouldCreateRoomWhenGivenRoomArguments() {
        //Given
        final Room room = new Room("name",10L,10L);

        //When
        underTest.createRoom("name",10L,10L);

        //Then
        Mockito.verify(roomRepo).save(room);
    }

    @Test
    public void testDeleteRoomShouldDeleteRoomWhenGivenMatchingRoomName() {
        //Given
        final Room room = new Room("name",10L,10L);
        BDDMockito.given(roomRepo.searchRoomByRoomName("name")).willReturn(room);

        //When
        underTest.deleteRoom("name");

        //Then
        Mockito.verify(roomRepo).delete(room);
    }

    @Test
    public void testUpdateRoomShouldUpdateRoomWhenGivenCorrectRoomDetails() {
        //Given
        final Room room = new Room("name",10L,10L);
        BDDMockito.given(roomRepo.searchRoomByRoomName("name")).willReturn(room);

        //When
        underTest.updateRoom("name",20L,20L);

        //Then
        Mockito.verify(roomRepo).save(room);
    }

}
