package com.example.CentralDEHelpingHands.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class RequestTest {
    private Request request;
    private Recipient recipient1, recipient2;
    private Volunteer vol1, vol2;

    @BeforeEach
    void setUp() {
        recipient1 = new Recipient();
        vol1 = new Volunteer();
        vol2 = new Volunteer();
        request = new Request(7L,"Pick-up", "I need a prescription picked up", recipient1, vol1 );
    }

    @Test
    void getId() {
        Assertions.assertEquals(7L, request.getId());
    }

    @Test
    void setId() {
        request.setId(8L);
        Assertions.assertEquals(8L, request.getId());
    }

    @Test
    void getDatePosted() {
        Assertions.assertEquals(LocalDate.of(2020, 3, 27), request.getDatePosted());
    }

    @Test
    void setDatePosted() {
        request.setDatePosted(LocalDate.of(2020, 3, 25));
        Assertions.assertEquals(LocalDate.of(2020, 3, 25), request.getDatePosted());
    }

    @Test
    void getTypeOfRequest() {
        Assertions.assertEquals("Pick-up", request.getTypeOfRequest());
    }

    @Test
    void setTypeOfRequest() {
        request.setTypeOfRequest("Food Delivery");
        Assertions.assertEquals("Food Delivery", request.getTypeOfRequest());
    }

    @Test
    void getRequestDescription() {
        Assertions.assertEquals("I need a prescription picked up", request.getRequestDescription());
    }

    @Test
    void setRequestDescription() {
        request.setRequestDescription("I need a meal delivered");
        Assertions.assertEquals("I need a meal delivered", request.getRequestDescription());
    }

    @Test
    void getRecipient() {
        Assertions.assertEquals(recipient1, request.getRecipient());
    }

    @Test
    void setRecipient() {
        request.setRecipient(recipient2);
        Assertions.assertEquals(recipient2, request.getRecipient());
    }

    @Test
    void getRequestStatus() {
        Assertions.assertEquals(RequestStatus.OPEN, request.getRequestStatus());
    }

    @Test
    void setRequestStatus() {
        request.setRequestStatus(RequestStatus.IN_PROGRESS);
        Assertions.assertEquals(RequestStatus.IN_PROGRESS, request.getRequestStatus());

    }

    @Test
    void getVolunteer() {
        Assertions.assertEquals(vol1, request.getVolunteer());
    }

    @Test
    void setVolunteer() {
        request.setVolunteer(vol2);
        Assertions.assertEquals(vol2, request.getVolunteer());
    }
}