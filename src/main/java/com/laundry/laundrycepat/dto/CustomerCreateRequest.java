package com.laundry.laundrycepat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreateRequest {

    private String name;

    private String address;

    private String phoneNumber;
}
