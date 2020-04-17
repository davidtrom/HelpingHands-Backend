package com.example.CentralDEHelpingHands.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Request {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate datePosted = LocalDate.now();
    private String typeOfRequest;
    private String requestDescription;
    @ManyToOne
    //@JsonIgnore
    private Recipient recipient;
    @OneToOne
    private Volunteer volunteer;
    private RequestStatus requestStatus = RequestStatus.OPEN;

    public Request() {
    }

    public Request(Long id, String typeOfRequest, String requestDescription, Recipient recipient, Volunteer volunteer) {
        this.id = id;
        this.typeOfRequest = typeOfRequest;
        this.requestDescription = requestDescription;
        this.recipient = recipient;
        this.volunteer = volunteer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public String getTypeOfRequest() {
        return typeOfRequest;
    }

    public void setTypeOfRequest(String typeOfRequest) {
        this.typeOfRequest = typeOfRequest;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    @Override
    public String toString() {
        return "Request:" + "\n" +
                "Date Posted: " + datePosted + "\n" +
                "Type Of Request " + typeOfRequest + "\n" +
                "Request Description " + requestDescription + "\n" +
                "Request Status: " + requestStatus;
    }
}
