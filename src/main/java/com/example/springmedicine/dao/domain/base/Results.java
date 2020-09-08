package com.example.springmedicine.dao.domain.base;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Results implements Serializable {
    private static final long serialVersionUID = -8457257838264102466L;
    private Integer code;
    private String text;
    private Date date;
}
