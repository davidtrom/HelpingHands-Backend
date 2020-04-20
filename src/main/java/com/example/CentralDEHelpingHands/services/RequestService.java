package com.example.CentralDEHelpingHands.services;

import com.example.CentralDEHelpingHands.emails.SendEmailToRecipient;
import com.example.CentralDEHelpingHands.entities.Recipient;
import com.example.CentralDEHelpingHands.entities.Request;
import com.example.CentralDEHelpingHands.entities.RequestStatus;
import com.example.CentralDEHelpingHands.entities.Volunteer;
import com.example.CentralDEHelpingHands.repositories.RecipientRepository;
import com.example.CentralDEHelpingHands.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CentralDEHelpingHands.repositories.RequestRepository;

import java.util.*;


@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    public Request createRequest (Request request){
        Request newRequest = new Request();
        newRequest.setTypeOfRequest(request.getTypeOfRequest());
        newRequest.setRequestDescription(request.getRequestDescription());
        //newRequest.setRecipient(recipientRepository.findById(recipientId).get());
        return requestRepository.save(request);
    }

    public Request updateRequest (Request request){
        Request requestToUpdate = requestRepository.findById(request.getId()).get();
        requestToUpdate.setTypeOfRequest(request.getTypeOfRequest());
        requestToUpdate.setRequestDescription(request.getRequestDescription());
        return requestRepository.save(request);
    }

    public Iterable<Request> displayAllRequestsByDatePosted(){
        return requestRepository.findAll();
    }

    public Boolean deleteRequest(Long id){
        if(requestRepository.findById(id).isPresent()){
            requestRepository.deleteById(id);
            return true;
        }
        else return false;
    }
    
    public Request updateStatus (Long requestId, String volEmail){

        Volunteer myVolunteer = volunteerRepository.findByEmail(volEmail);
        List<Request> requestList = myVolunteer.getAgreedRequests();
        Request requestToUpdate = requestRepository.findById(requestId).get();
        if(requestToUpdate.getRequestStatus().equals(RequestStatus.OPEN)){
            requestList.add(requestToUpdate);
            myVolunteer.setAgreedRequests(requestList);
            volunteerRepository.save(myVolunteer);
            requestToUpdate.setRequestStatus(RequestStatus.IN_PROGRESS);
            SendEmailToRecipient.sendMessageToRecipient("davidtrom@hotmail.com", requestToUpdate.getRecipient().getFirstName(), requestToUpdate, myVolunteer.getFirstName(), myVolunteer.getLastName(), myVolunteer.getPhoneNum(), myVolunteer.getEmail(), myVolunteer.getLink());
            requestToUpdate.setVolunteer(myVolunteer);
        }
        return requestRepository.save(requestToUpdate);
    }

    //In Case Volunteer can't complete request, they can change status.
    public Request freeRequest (Long requestId){
        Request myRequest = requestRepository.findById(requestId).get();
        Volunteer myVolunteer = myRequest.getVolunteer();
        List<Request> requestList = myVolunteer.getAgreedRequests();
        requestList.remove(myRequest);
        myVolunteer.setAgreedRequests(requestList);
        volunteerRepository.save(myVolunteer);
        myRequest.setVolunteer(null);
        myRequest.setRequestStatus(RequestStatus.OPEN);
        //Send an email??
        return requestRepository.save(myRequest);
    }

    public Request showRequestDetails(Long requestId) {
        return requestRepository.findById(requestId).get();
    }

    public Iterable<Request> getThisRecipentRequests (Long recipientId) {
        return requestRepository.findAllByRecipient_Id(recipientId);
    }

    public Iterable<Request> getThisVolunteerRequests (Long volunteerId) {
        return requestRepository.findAllByVolunteer_Id(volunteerId);
    }
}
