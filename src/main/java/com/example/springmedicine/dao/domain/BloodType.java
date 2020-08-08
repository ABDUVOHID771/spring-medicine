package com.example.springmedicine.dao.domain;

public enum BloodType {

    SORT_1(1), SORT_2(2), SORT_3(3);

    private final Integer value;

    BloodType(Integer i) {
        this.value = i;
    }

    public Integer getValue() {
        return value;
    }

}
