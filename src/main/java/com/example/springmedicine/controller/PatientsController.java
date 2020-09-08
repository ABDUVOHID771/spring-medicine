package com.example.springmedicine.controller;

import com.example.springmedicine.constants.ApiResults;
import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.dao.domain.base.Results;
import com.example.springmedicine.dao.dto.DoctorsDto;
import com.example.springmedicine.dao.dto.ListPatientsDto;
import com.example.springmedicine.dao.dto.PatientsDto;
import com.example.springmedicine.service.DoctorsService;
import com.example.springmedicine.service.PatientsService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.*;

@Slf4j
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

    @GetMapping
    public ResponseEntity<?> read(@RequestHeader Map<String, String> headers) {
        String baseId = headers.get("base_id");
        if (!Strings.isNullOrEmpty(baseId)) {
            try {
                Patients patients = patientsService.get(Long.valueOf(baseId));
                PatientsDto dto = new PatientsDto(ApiResults.OK, patients);
                return new ResponseEntity<>(dto, HttpStatus.OK);
            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(ApiResults.ERR_001, HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResults.ERR_003);
        }


    }

    @GetMapping("/all")
    public ResponseEntity<?> readAll(@RequestHeader Map<String, String> headers) {
        String baseId = headers.get("base_id");
        Doctors doctors;
        if (!Strings.isNullOrEmpty(baseId)) {
            try {
                doctors = doctorsService.get(Long.valueOf(baseId));
            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(ApiResults.ERR_001, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(ApiResults.ERR_001, HttpStatus.NOT_FOUND);
        }

        try {
            List<Patients> patients = patientsService.getAllByPatients(doctors);
            ListPatientsDto patientsDto = new ListPatientsDto(patients, ApiResults.OK);
            return new ResponseEntity<>(patientsDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(ApiResults.ERR_002, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // EDIT

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Patients input, @RequestHeader Map<String, String> headers) {

        if (Strings.isNullOrEmpty(headers.get("base_id"))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResults.ERR_002);
        }
        Long baseId = Long.valueOf(headers.get("base_id"));

        if (input.getBaseId() != null && !input.getBaseId().equals(baseId)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT).body(ApiResults.ERR_004);
        }
        try {
            Patients patients = patientsService.update(input);
            PatientsDto dto = new PatientsDto(ApiResults.OK, patients);
            return ResponseEntity
                    .ok().body(dto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).body(ApiResults.ERR_001);
        }

    }

    // DELETE

    @DeleteMapping("/{baseId}")
    public ResponseEntity<Void> delete(@PathVariable Long baseId) {
        try {
            patientsService.delete(baseId);
            return ResponseEntity
                    .noContent().build();
        } catch (Exception e) {
            return ResponseEntity
                    .notFound().build();
        }
    }

}

