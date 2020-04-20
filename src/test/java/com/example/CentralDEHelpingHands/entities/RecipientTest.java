package com.example.CentralDEHelpingHands.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class RecipientTest {
    private Recipient recipient;
    private Request r1, r2, r3, r4, r5, r6;
    //private List<Request> requestList = new ArrayList<>(0);

    @BeforeEach
    void setUp() {
        recipient = new Recipient(2L, "Person", "InNeed", "302-555-9876", "helpme@gmail.com", "Th!sPassword7", "Hartley", "www.linkedin.com/davidtrom");
//        List<Request> theList = recipient.getRequests();
//        theList.add(r1);
//        theList.add(r2);
//        theList.add(r3);
//        theList.add(r4);
//        recipient.setRequests(theList);
    }

    @Test
    void getId() {
        Assertions.assertEquals(2L, recipient.getId());
    }

    @Test
    void setId() {
        recipient.setId(5L);
        Assertions.assertEquals(5L, recipient.getId());
    }

    @Test
    void getFirstName() {
        Assertions.assertEquals("Person", recipient.getFirstName());
    }

    @Test
    void setFirstName() {
        recipient.setFirstName("John");
        Assertions.assertEquals("John", recipient.getFirstName());
    }

    @Test
    void getLastName() {
        Assertions.assertEquals("InNeed", recipient.getLastName());
    }

    @Test
    void setLastName() {
        recipient.setLastName("Doe");
        Assertions.assertEquals("Doe", recipient.getLastName());
    }

    @Test
    void getPhoneNum() {
        Assertions.assertEquals("302-555-9876", recipient.getPhoneNum());
    }

    @Test
    void setPhoneNum() {
        recipient.setPhoneNum("302-555-1234");
        Assertions.assertEquals("302-555-1234", recipient.getPhoneNum());
    }

    @Test
    void getEmail() {
        Assertions.assertEquals("helpme@gmail.com", recipient.getEmail());
    }

    @Test
    void setEmail() {
        recipient.setEmail("inNeed@gmail.com");
        Assertions.assertEquals("inNeed@gmail.com", recipient.getEmail());
    }

    @Test
    void getPassword() {
        Assertions.assertEquals("Th!sPassword7", recipient.getPassword());
    }

    @Test
    void setPassword() {
        recipient.setPassword("NewP@ssword9");
        System.out.println(recipient.getPassword());
        Assertions.assertTrue(recipient.getPassword() != null);
    }

    @Test
    void getLocation() {
        Assertions.assertEquals("Hartley", recipient.getLocation());
    }

    @Test
    void setLocation() {
        recipient.setLocation("Magnolia");
        Assertions.assertEquals("Magnolia", recipient.getLocation());
    }

    @Test
    void getLink() {
        Assertions.assertEquals("www.linkedin.com/davidtrom", recipient.getLink());
    }

    @Test
    void setLink() {
        recipient.setLink("www.facebook.com/daveT");
        Assertions.assertEquals("www.facebook.com/daveT", recipient.getLink());
    }

//    @Test
//    void getRequests() {
//        Assertions.assertEquals(4, recipient.getRequests().size());
//    }
//
//    @Test
//    void setRequests() {
//        List<Request> thisList = recipient.getRequests();
//        thisList.add(r5);
//        thisList.add(r6);
//        recipient.setRequests(thisList);
//        Assertions.assertEquals(thisList, recipient.getRequests());
//    }
//
//    @Test
//    void setRequests2() {
//        List<Request> thisList = recipient.getRequests();
//        thisList.add(r5);
//        thisList.add(r6);
//        recipient.setRequests(thisList);
//        Assertions.assertEquals(recipient.getRequests().size(), 6);
//    }
}