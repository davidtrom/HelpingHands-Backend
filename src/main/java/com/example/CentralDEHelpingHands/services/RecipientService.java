package com.example.CentralDEHelpingHands.services;

import com.example.CentralDEHelpingHands.entities.Recipient;
import com.example.CentralDEHelpingHands.entities.Request;
import com.example.CentralDEHelpingHands.repositories.RecipientRepository;
import com.example.CentralDEHelpingHands.validators.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class RecipientService {

    @Autowired
    private RecipientRepository recipientRepository;

    public Recipient createRecipient(Recipient recipient) {
//        Recipient newRecipient = new Recipient();
//        newRecipient.setFirstName(recipient.getFirstName());
//        newRecipient.setLastName(recipient.getLastName());
//        newRecipient.setPhoneNum(recipient.getPhoneNum());
//        newRecipient.setEmail(recipient.getEmail());
//        System.out.println(recipient.getPassword());
//        //newRecipient.setPassword(recipient.getPassword());
//        newRecipient.setLocation(recipient.getLocation());
//        newRecipient.setLink(recipient.getLink());
        return recipientRepository.save(recipient);
    }

    public Boolean emailAvailable(String email) {
        Iterable<Recipient> recipients = recipientRepository.findAll();
        for(Recipient r: recipients){
            if(r.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public Boolean deleteRecipient(Long id) {
        if (recipientRepository.findById(id).isPresent()) {
            recipientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Recipient verifyRecipient(String email, String password) {
        Recipient recipientToVerify = recipientRepository.findByEmail(email);
        String[] storedInfo = recipientToVerify.getPassword().split(":");
        String salt = storedInfo[0];
        String storedPassword = storedInfo[1];
        System.out.println(salt + " " + storedPassword);
        if (PasswordUtils.verifyUserPassword(password, storedPassword, salt)) {
            System.out.println("recipientToVerify in Service " + recipientToVerify);
            return recipientToVerify;
        } else {
            System.out.println("in else block");
            return null;
        }
    }

    public Recipient getRecipient(Long recipientId){
        return recipientRepository.findById(recipientId).get();
    }

    public Recipient editRecipientProfile (Recipient recipient){
        Recipient recipientToEdit = recipientRepository.findById(recipient.getId()).get();
        recipientToEdit.setFirstName(recipient.getFirstName());
        recipientToEdit.setLastName(recipient.getLastName());
        recipientToEdit.setPhoneNum(recipient.getPhoneNum());
        recipientToEdit.setEmail(recipient.getEmail());
        recipientToEdit.setLocation(recipient.getLocation());
        recipientToEdit.setLink(recipient.getLink());
        return recipientRepository.save(recipientToEdit);
    }

    public Recipient getRecipientByEmail (String email) {
        return recipientRepository.findByEmail(email);
    }
}
