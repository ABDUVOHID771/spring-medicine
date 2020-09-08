package com.example.springmedicine.controller;

import com.example.springmedicine.constants.ApiResults;
import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.dto.DoctorsDto;
import com.example.springmedicine.dao.dto.ListDoctorsDto;
import com.example.springmedicine.service.DoctorsService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Map;

@Slf4j
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

    @GetMapping
    public ResponseEntity<?> read(@RequestHeader Map<String, String> headers) {
        String baseId = headers.get("base_id");
        if (!Strings.isNullOrEmpty(baseId)) {
            try {
                Doctors doctors = doctorsService.get(Long.valueOf(baseId));
                DoctorsDto dto = new DoctorsDto(doctors, ApiResults.OK);
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
    public ResponseEntity<?> readAll() {

        return ResponseEntity
                .ok()
                .body(new ListDoctorsDto(doctorsService.getAll(), ApiResults.OK));
    }

    // EDIT

    @PutMapping
    public ResponseEntity<?> update(@RequestHeader Map<String, String> headers, @RequestBody Doctors input) {
        if (Strings.isNullOrEmpty(headers.get("base_id"))) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResults.ERR_002);
        }

        Long baseId = Long.valueOf(headers.get("base_id"));

        if (input.getBaseId() != null && !input.getBaseId().equals(baseId)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT).body(ApiResults.ERR_004);
        }
        try {
            Doctors doctors = doctorsService.update(input);
            DoctorsDto dto = new DoctorsDto(doctors, ApiResults.OK);
            return ResponseEntity
                    .ok().body(dto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).body(ApiResults.ERR_001);
        }

    }

    // DELETE

//    @DeleteMapping("/{baseId}")
//    public ResponseEntity<Void> delete(@PathVariable Long baseId) {
//        try {
//            doctorsService.delete(baseId);
//            return ResponseEntity
//                    .noContent().build();
//        } catch (Exception e) {
//            return ResponseEntity
//                    .notFound().build();
//        }
//    }

}
