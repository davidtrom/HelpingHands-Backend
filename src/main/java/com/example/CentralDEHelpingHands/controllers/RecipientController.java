package com.example.CentralDEHelpingHands.controllers;

import com.example.CentralDEHelpingHands.entities.Recipient;
import com.example.CentralDEHelpingHands.entities.Request;
import com.example.CentralDEHelpingHands.services.RecipientService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipients")
class RecipientController {

    @Autowired
    private RecipientService recipientService;

    @PostMapping("/create")
    public ResponseEntity <Recipient> createRecipient (@RequestBody Recipient recipient){
        return new ResponseEntity<>(recipientService.createRecipient(recipient), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity <Boolean> deleteRecipient (@PathVariable Long id){
        return new ResponseEntity<>(recipientService.deleteRecipient(id), HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<Recipient> verifyRecipient (@RequestBody String data) throws JSONException {
        JSONObject jsonData = new JSONObject(data);
        String email = (String) jsonData.get("email");
        String password = (String) jsonData.get("password");
        Recipient verifiedRecipient = recipientService.verifyRecipient(email, password);
        System.out.println(verifiedRecipient);
        return (verifiedRecipient != null) ? new ResponseEntity<>(verifiedRecipient, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/check-email")
    public ResponseEntity<Boolean> isVolunteerEmailAvailable (@RequestBody String data) throws JSONException {
        JSONObject jsonData = new JSONObject(data);
        String email = (String) jsonData.get("email");
        return new ResponseEntity<>(recipientService.emailAvailable(email), HttpStatus.OK);
    }

    @GetMapping("/{recipientId}")
    public ResponseEntity<Recipient> getRecipientById (@PathVariable Long recipientId){
        return new ResponseEntity<>(recipientService.getRecipient(recipientId), HttpStatus.OK);
    }

    @PostMapping("/edit-recipient")
    public ResponseEntity<Recipient> editRecipentProfile (@RequestBody Recipient recipient){
        return new ResponseEntity<>(recipientService.editRecipientProfile(recipient), HttpStatus.OK);
    }

}
