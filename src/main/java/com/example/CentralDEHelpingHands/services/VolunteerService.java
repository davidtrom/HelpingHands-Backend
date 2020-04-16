package com.example.CentralDEHelpingHands.services;

import com.example.CentralDEHelpingHands.entities.Recipient;
import com.example.CentralDEHelpingHands.entities.Volunteer;
import com.example.CentralDEHelpingHands.repositories.VolunteerRepository;
import com.example.CentralDEHelpingHands.validators.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Volunteer createVolunteer (Volunteer volunteer){
        Volunteer newVolunteer = new Volunteer();
        newVolunteer.setFirstName(volunteer.getFirstName());
        newVolunteer.setLastName(volunteer.getLastName());
        newVolunteer.setPhoneNum(volunteer.getPhoneNum());
        newVolunteer.setEmail(volunteer.getEmail());
        newVolunteer.setPassword(volunteer.getPassword());
        newVolunteer.setLink(volunteer.getLink());
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(Long id, String firstName, String lastName, String phoneNum, String email, String link){
        Volunteer volunteerToUpdate = volunteerRepository.findById(id).get();
        volunteerToUpdate.setFirstName(firstName);
        volunteerToUpdate.setLastName(lastName);
        volunteerToUpdate.setPhoneNum(phoneNum);
        volunteerToUpdate.setEmail(email);
        volunteerToUpdate.setLink(link);
        return volunteerRepository.save(volunteerToUpdate);
    }

    public Iterable<Volunteer> findAllVolunteers () {
        return volunteerRepository.findAll();
    }

    public Boolean emailAvailable (String email){
        Iterable<Volunteer> volunteers = volunteerRepository.findAll();
        for(Volunteer v : volunteers){
            if(v.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public Boolean deleteVolunteerById (Long id){
        if(volunteerRepository.findById(id).isPresent()){
            volunteerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Volunteer verifyVolunteer(String email, String password){
        Volunteer volunteerToVerify = volunteerRepository.findByEmail(email);
        String[] storedInfo = volunteerToVerify.getPassword().split(":");
        String salt = storedInfo[0];
        String storedPassword = storedInfo[1];
        if(PasswordUtils.verifyUserPassword(password, storedPassword, salt)){
            return volunteerToVerify;
        }
        else return null;
    }
}
