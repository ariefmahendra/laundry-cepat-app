package com.laundry.laundrycepat.dto;

import com.laundry.laundrycepat.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BillDetailCreateResponse {
    private String id;
    private String billId;
    private ProductEntity product;
    private BigDecimal productPrice;
    private int quantity;
}
