package com.example.springmedicine.dao.dto;

import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.dao.domain.base.Results;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientsDto {

    private Results results;
    private Patients patient;

}
