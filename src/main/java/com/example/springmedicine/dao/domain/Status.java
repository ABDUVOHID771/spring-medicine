package com.example.springmedicine.dao.domain;

public enum Status {

    MIDDLE(1), SENIOR(2), EXPERT(3);

    private final Integer value;


    Status(Integer i) {
        this.value
                = i;
    }

    public Integer getValue() {
        return value;
    }

}
