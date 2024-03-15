package com.laundry.laundrycepat.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductCreateRequest {
    private String name;
    private String unit;
    private BigDecimal price;
}
