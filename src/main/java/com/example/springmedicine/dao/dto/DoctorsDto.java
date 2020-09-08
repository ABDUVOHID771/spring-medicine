package com.example.springmedicine.dao.dto;

import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.dao.domain.Status;
import com.example.springmedicine.dao.domain.base.Results;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorsDto {

    private Doctors doctors;
    private Results results;
}
