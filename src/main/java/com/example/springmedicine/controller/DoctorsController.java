package com.example.springmedicine.controller;

import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.service.DoctorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {

    private final DoctorsService doctorsService;

    public DoctorsController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    @PostMapping
    public ResponseEntity<Doctors> create(@RequestBody Doctors input) {
        return new ResponseEntity<>(doctorsService.create(input), HttpStatus.CREATED);

    }

    // GET

    @GetMapping("/{uuid}")
    public ResponseEntity<Doctors> read(@PathVariable UUID uuid) {
        try {
            return ResponseEntity
                    .ok()
                    .body(doctorsService.get(uuid));
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> readAll() {

        return ResponseEntity
                .ok()
                .body(doctorsService.getAll());
    }

    // EDIT

    @PutMapping("/{uuid}")
    public ResponseEntity<Doctors> update(@RequestBody Doctors input, @PathVariable UUID uuid) {

        if (input.getUuid() != null && !input.getUuid().equals(uuid)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT).build();
        }
        try {
            input.setUuid(uuid);
            return ResponseEntity
                    .ok().body(doctorsService.update(input));
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }

    }

    // DELETE

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        try {
            doctorsService.delete(uuid);
            return ResponseEntity
                    .noContent().build();
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }

}
