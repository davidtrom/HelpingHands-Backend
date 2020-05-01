package com.example.CentralDEHelpingHands.controllers;
import com.example.CentralDEHelpingHands.entities.Request;
import org.json.JSONObject;
import org.json.JSONException;
import com.example.CentralDEHelpingHands.entities.Volunteer;
import com.example.CentralDEHelpingHands.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http//localhost:4200")
@RequestMapping("/volunteers")
class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @PostMapping("/create")
    public ResponseEntity<Volunteer> addVolunteer (@RequestBody Volunteer volunteer){
        return new ResponseEntity<>(volunteerService.createVolunteer(volunteer), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<Volunteer> findVolunteerByEmail(String email){
        return null;
    }

    @GetMapping("/find-all")
    public ResponseEntity<Iterable<Volunteer>> findAllVolunteers (){
        return new ResponseEntity<>(volunteerService.findAllVolunteers(), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteVolunteerById (@PathVariable Long id){
        return new ResponseEntity<>(volunteerService.deleteVolunteerById(id), HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<Volunteer> verifyVolunteer (@RequestBody String data) throws JSONException {
        JSONObject jsonData = new JSONObject(data);
        String email = (String) jsonData.get("email");
        String password = (String) jsonData.get("password");
        Volunteer verifiedVolunteer = volunteerService.verifyVolunteer(email, password);
        return (verifiedVolunteer != null) ? new ResponseEntity<>(verifiedVolunteer, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/check-email")
    public ResponseEntity<Boolean> isVolunteerEmailAvailable (@RequestBody String data) throws JSONException {
        JSONObject jsonData = new JSONObject(data);
        String email = (String) jsonData.get("email");
        return new ResponseEntity<>(volunteerService.emailAvailable(email), HttpStatus.OK);
    }

    @PostMapping("/update-profile")
    public ResponseEntity<Volunteer> updateVolProfile (@RequestBody String data) throws JSONException {
        JSONObject jsonData = new JSONObject(data);
        Long id = (Long) jsonData.get("id");
        String firstName = (String) jsonData.get("firstName");
        String lastName = (String) jsonData.get("lastName");
        String phoneNum = (String) jsonData.get("phoneNum");
        String link = (String) jsonData.get("link");
        //System.out.println(lastName + " " + firstName);
        return new ResponseEntity<>(volunteerService.updateVolunteer(id, firstName, lastName, phoneNum, link), HttpStatus.OK);
    }

    @GetMapping("/get/{volunteerId}")
    public ResponseEntity<Volunteer> getVolunteer (@PathVariable Long volunteerId){
        return new ResponseEntity<>(volunteerService.getVolunteer(volunteerId), HttpStatus.OK);
    }

    @GetMapping("/{volunteerEmail}/get-by-email")
    public ResponseEntity<Volunteer> getVolunteerByEmail(@PathVariable String volunteerEmail){
        return new ResponseEntity<>(volunteerService.getVolunteerByEmail(volunteerEmail), HttpStatus.OK);
    }

    @PostMapping("/update-email")
    public ResponseEntity<Volunteer> updateLogin(@RequestBody String data) throws JSONException {
        JSONObject jsonData = new JSONObject(data);
        String currentEmail = (String) jsonData.get("currentEmail");
        String newEmail = (String) jsonData.get("newEmail");
        return new ResponseEntity<>(volunteerService.updateVolunteerEmail(currentEmail, newEmail), HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody String data) throws JSONException {
        JSONObject jsonData = new JSONObject(data);
        Long volunteerId = (Long) jsonData.get("id");
        String password = (String) jsonData.get("password");
        String newPassword = (String) jsonData.get("newPassword");
        return new ResponseEntity<>(volunteerService.updateVolunteerPassword(volunteerId, password, newPassword), HttpStatus.OK);
    }
}



