package com.example.CentralDEHelpingHands.repositories;

import com.example.CentralDEHelpingHands.entities.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    Iterable<Request> findAllByVolunteer_Id(Long volunteerId);
    Iterable<Request> findAllByRecipient_Id(Long recipientId);
}
