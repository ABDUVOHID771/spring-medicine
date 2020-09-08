package com.example.springmedicine.dao.dto;

import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.domain.base.Results;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListDoctorsDto {
    private List<Doctors> doctors;
    private Results results;
}
