package com.laundry.laundrycepat.controller;

import com.laundry.laundrycepat.dto.TransactionCreateResponse;
import com.laundry.laundrycepat.dto.TxBillCreateRequest;
import com.laundry.laundrycepat.dto.WebResponse;
import com.laundry.laundrycepat.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // create new transaction
    @PostMapping("/api/transactions")
    public WebResponse<TransactionCreateResponse> createTransaction(@RequestBody TxBillCreateRequest payload) {
        TransactionCreateResponse response = transactionService.save(payload);
        return WebResponse.<TransactionCreateResponse>builder()
                .message("Transaction created successfully")
                .data(response)
                .build();
    }

    // list transaction
    @GetMapping("/api/transactions")
    public WebResponse<List<TransactionCreateResponse>> listTransaction() {
        List<TransactionCreateResponse> response = transactionService.getAll();
        return WebResponse.<List<TransactionCreateResponse>>builder()
                .message("Transaction list")
                .data(response)
                .build();
    }
}
