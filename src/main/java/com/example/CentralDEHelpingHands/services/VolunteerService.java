package com.example.CentralDEHelpingHands.services;

import com.example.CentralDEHelpingHands.entities.Request;
import com.example.CentralDEHelpingHands.entities.Volunteer;
import com.example.CentralDEHelpingHands.repositories.VolunteerRepository;
import com.example.CentralDEHelpingHands.validators.EmailValidator;
import com.example.CentralDEHelpingHands.validators.PasswordUtils;
import com.example.CentralDEHelpingHands.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Volunteer createVolunteer (Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(Long id, String firstName, String lastName, String phoneNum, String link){
        Volunteer volunteerToUpdate = volunteerRepository.findById(id).get();
        volunteerToUpdate.setFirstName(firstName);
        volunteerToUpdate.setLastName(lastName);
        volunteerToUpdate.setPhoneNum(phoneNum);
        volunteerToUpdate.setLink(link);
        return volunteerRepository.save(volunteerToUpdate);
    }

    public Iterable<Volunteer> findAllVolunteers () {
        return volunteerRepository.findAll();
    }

    public Boolean emailTaken (String email){
        Volunteer thisVolunteer = volunteerRepository.findByEmail(email);
        if(thisVolunteer == null){
            return false;
        }
        else return true;

//        Boolean check = null;
//        Iterable<Volunteer> volunteers = volunteerRepository.findAll();
//        for(Volunteer v : volunteers){
//            if(v.getEmail().equals(email)) {
//                System.out.println(v.getEmail());
//                check = true;
//                //System.out.println(check);
//            }
//            else check = false;
//        }
//        System.out.println(check);
//        return check;
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

    public Volunteer getVolunteer(Long volunteeerId){
        return volunteerRepository.findById(volunteeerId).get();
    }

    public Volunteer getVolunteerByEmail (String volunteerEmail){
        return volunteerRepository.findByEmail(volunteerEmail);
    }

    public Volunteer updateVolunteerEmail(String currentEmail, String newEmail){
        Volunteer volunteerToUpdate = volunteerRepository.findByEmail(currentEmail);
//        if(emailTaken(newEmail)){
            volunteerToUpdate.setEmail(newEmail);
            return volunteerRepository.save(volunteerToUpdate);
//        }
//        else return null;
    }

    public Boolean updateVolunteerPassword(Long volunteerId, String password, String newPassword){
        Volunteer volunteerToUpdate = volunteerRepository.findById(volunteerId).get();
        String [] passwordInfo = volunteerToUpdate.getPassword().split(":");
        boolean verified = PasswordUtils.verifyUserPassword(password, passwordInfo[1], passwordInfo[0]);
        if(verified){
            volunteerToUpdate.setPassword(newPassword);
            volunteerRepository.save(volunteerToUpdate);
            return true;
        }
        else return false;
    }
}
