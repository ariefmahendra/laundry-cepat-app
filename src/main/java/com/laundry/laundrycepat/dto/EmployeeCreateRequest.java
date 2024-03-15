package com.laundry.laundrycepat.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeCreateRequest {
    private String name;

    private String address;

    private String phoneNumber;
}
