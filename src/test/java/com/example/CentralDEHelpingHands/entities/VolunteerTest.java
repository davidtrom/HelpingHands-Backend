package com.example.CentralDEHelpingHands.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class VolunteerTest {
    private Volunteer volunteer;
    private Request r1, r2, r3, r4, r5;

    @BeforeEach
    void setUp() {
        volunteer = new Volunteer(1L, "Jane", "Doe", "302-123-5556", "janedoe@gmail.com", "J@neDoe1", "www.linkedin.com/janedoe");
    }

    @Test
    void getId() {
        Assertions.assertEquals(1L, volunteer.getId());
    }

    @Test
    void setId() {
        volunteer.setId(6L);
        Assertions.assertEquals(6L, volunteer.getId());
    }

    @Test
    void getFirstName() {
        Assertions.assertEquals("Jane", volunteer.getFirstName());
    }

    @Test
    void setFirstName() {
        volunteer.setFirstName("Sue");
        Assertions.assertEquals("Sue", volunteer.getFirstName());
    }

    @Test
    void getLastName() {
        Assertions.assertEquals("Doe", volunteer.getLastName());
    }

    @Test
    void setLastName() {
        volunteer.setLastName("Smith");
        Assertions.assertEquals("Smith", volunteer.getLastName());
    }

    @Test
    void getPhoneNum() {
        Assertions.assertEquals("302-123-5556", volunteer.getPhoneNum());
    }

    @Test
    void setPhoneNum() {
        volunteer.setPhoneNum("302-555-3210");
        Assertions.assertEquals("302-555-3210", volunteer.getPhoneNum());
    }

    @Test
    void getEmail() {
        Assertions.assertEquals("janedoe@gmail.com", volunteer.getEmail());
    }

    @Test
    void setEmail() {
        volunteer.setEmail("suesmith@gmail.com");
        Assertions.assertEquals("suesmith@gmail.com", volunteer.getEmail());
    }

    @Test
    void getPassword() {
        Assertions.assertEquals("J@neDoe1", volunteer.getPassword());
    }

    @Test
    void setPassword() {
        volunteer.setPassword("SueSm!th5");
        System.out.println(volunteer.getPassword());
        Assertions.assertTrue(volunteer.getPassword() != null);
    }

    @Test
    void getLink() {
        Assertions.assertEquals("www.linkedin.com/janedoe", volunteer.getLink());
    }

    @Test
    void setLink() {
        volunteer.setLink("www.facebook.com/suesmith");
        Assertions.assertEquals("www.facebook.com/suesmith", volunteer.getLink());
    }

    @Test
    void getAgreedRequests() {
        Assertions.assertEquals(volunteer.getAgreedRequests().size(), 0);
    }

    @Test
    void getAgreedRequests2() {
        List<Request> theList = volunteer.getAgreedRequests();
        theList.add(r1);
        theList.add(r3);
        theList.add(r5);
        volunteer.setAgreedRequests(theList);
        Assertions.assertEquals(volunteer.getAgreedRequests().size(), 3);
    }

    @Test
    void setAgreedRequests() {
        List<Request> theList = volunteer.getAgreedRequests();
        theList.add(r2);
        theList.add(r4);
        volunteer.setAgreedRequests(theList);
        Request [] expectedArray = theList.toArray(new Request[theList.size()]);
        Request [] actualArray =  volunteer.getAgreedRequests().toArray(new Request[volunteer.getAgreedRequests().size()]);
        Assertions.assertArrayEquals(expectedArray, actualArray);
    }
}
