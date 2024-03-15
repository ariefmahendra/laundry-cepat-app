package com.laundry.laundrycepat.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TxBillDetailCreateRequest {
    private String productId;
    private int qty;
}
