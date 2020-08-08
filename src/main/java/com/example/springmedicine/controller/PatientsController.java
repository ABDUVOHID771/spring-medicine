package com.example.springmedicine.controller;

import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.service.DoctorsService;
import com.example.springmedicine.service.PatientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    private final PatientsService patientsService;
    private final DoctorsService doctorsService;

    public PatientsController(PatientsService patientsService, DoctorsService doctorsService) {
        this.patientsService = patientsService;
        this.doctorsService = doctorsService;
    }

    @PostMapping
    public ResponseEntity<Patients> create(@RequestBody Patients input) {
        try {
            Set<Doctors> doctors = input.getDoctors();
            input.setDoctors(null);
            Patients created = patientsService.create(input);

            doctors.forEach(doctor -> {
                doctor.addPatients(Set.of(input));
                doctorsService.update(doctor);
            });
            return new ResponseEntity<>(created, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    // GET

    @GetMapping("/{uuid}")
    public ResponseEntity<Patients> read(@PathVariable UUID uuid) {
        try {
            return ResponseEntity
                    .ok()
                    .body(patientsService.get(uuid));
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        return ResponseEntity
                .ok()
                .body(patientsService.getAll());
    }

    // EDIT

    @PutMapping("/{uuid}")
    public ResponseEntity<Patients> update(@RequestBody Patients input, @PathVariable UUID uuid) {

        if (input.getUuid() != null && !input.getUuid().equals(uuid)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT).build();
        }
        try {
            input.setUuid(uuid);
            return ResponseEntity
                    .ok().body(patientsService.update(input));
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }

    }

    // DELETE

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        try {
            patientsService.delete(uuid);
            return ResponseEntity
                    .noContent().build();
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }

}

