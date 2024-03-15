package com.laundry.laundrycepat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class TxBillCreateRequest {
    private Date billDate;
    private Date entryDate;
    private Date finishDate;
    private String employeeId;
    private String customerId;
    private List<TxBillDetailCreateRequest> billDetails;
}
