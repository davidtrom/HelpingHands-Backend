package com.example.CentralDEHelpingHands.controllers;

import com.example.CentralDEHelpingHands.entities.Request;
import com.example.CentralDEHelpingHands.entities.Volunteer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CentralDEHelpingHands.services.RequestService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/requests")
class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public ResponseEntity<Iterable<Request>> displayAllRequestsbyDate (){
        return new ResponseEntity<>(requestService.displayAllRequestsByDatePosted(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Request> createRequest(@RequestBody Request request){
        return new ResponseEntity<>(requestService.createRequest(request),HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{requestId}")
    public ResponseEntity<Boolean> deleteRequest(@PathVariable Long requestId){
        return new ResponseEntity<>(requestService.deleteRequest(requestId), HttpStatus.OK);
    }

    @PostMapping("/{requestId}/update-status")
    public ResponseEntity<Request> updateStatus (@PathVariable Long requestId, @RequestBody String data) throws JSONException {
        JSONObject jsonData = new JSONObject(data);
        String volunteerEmail = (String) jsonData.get("email");
        requestService.updateStatus(requestId, volunteerEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Request> showRequestDetails (@PathVariable Long requestId) {
        return new ResponseEntity<>(requestService.showRequestDetails(requestId), HttpStatus.OK);
    }

    @PostMapping("/free-request/{requestId}")
    public ResponseEntity<Request> freeRequest (@PathVariable Long requestId){
        return new ResponseEntity<>(requestService.freeRequest(requestId), HttpStatus.OK);
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<Iterable<Request>> getRequestsForRecipient (@PathVariable Long recipientId){
        return new ResponseEntity<>(requestService.getThisRecipentRequests(recipientId), HttpStatus.OK);
    }

    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<Iterable<Request>> getRequestsForVolunteer (@PathVariable Long volunteerId){
        return new ResponseEntity<>(requestService.getThisVolunteerRequests(volunteerId), HttpStatus.OK);
    }

}
