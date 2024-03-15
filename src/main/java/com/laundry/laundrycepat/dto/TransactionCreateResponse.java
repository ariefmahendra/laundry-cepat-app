package com.laundry.laundrycepat.dto;

import com.laundry.laundrycepat.entity.CustomerEntity;
import com.laundry.laundrycepat.entity.EmployeeEntity;
import com.laundry.laundrycepat.entity.TxBillDetailEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TransactionCreateResponse {
    private String id;
    private Date billDate;
    private Date entryDate;
    private Date finishDate;
    private EmployeeEntity employee;
    private CustomerEntity customer;
    private List<BillDetailCreateResponse> txBillDetail;
    private BigDecimal totalBill;
}
