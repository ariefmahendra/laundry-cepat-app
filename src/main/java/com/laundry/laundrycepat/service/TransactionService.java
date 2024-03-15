package com.laundry.laundrycepat.service;

import com.fasterxml.jackson.core.io.BigDecimalParser;
import com.laundry.laundrycepat.dto.BillDetailCreateResponse;
import com.laundry.laundrycepat.dto.TransactionCreateResponse;
import com.laundry.laundrycepat.dto.TxBillCreateRequest;
import com.laundry.laundrycepat.dto.TxBillDetailCreateRequest;
import com.laundry.laundrycepat.entity.*;
import com.laundry.laundrycepat.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionCreateResponse save(TxBillCreateRequest payload) {
            CustomerEntity customerById = findCustomerById(payload.getCustomerId());

            EmployeeEntity employeeById = findEmployeeById(payload.getEmployeeId());

            BigDecimal totalBill = new BigDecimal("0");

            TxBillEntity txBill = Stream.of(new TxBillEntity())
                    .peek(p -> {
                        p.setBill_date(payload.getBillDate());
                        p.setEntryDate(payload.getEntryDate());
                        p.setFinishDate(payload.getFinishDate());
                        p.setEmployee(employeeById);
                        p.setCustomer(customerById);
                    }).findFirst().orElse(null);

            TxBillEntity bill = transactionRepository.save(txBill);

            TransactionCreateResponse response = Stream.of(new TransactionCreateResponse()).
                    peek(p -> {
                        p.setId(bill.getId());
                        p.setBillDate(bill.getBill_date());
                        p.setEntryDate(bill.getEntryDate());
                        p.setFinishDate(bill.getFinishDate());
                        p.setCustomer(customerById);
                        p.setEmployee(employeeById);
                    }).findFirst().orElse(null);

            response.setTxBillDetail(new ArrayList<>());

            for (TxBillDetailCreateRequest billDetail : payload.getBillDetails()) {
                ProductEntity productById = findProductById(billDetail.getProductId());

                BigDecimal price = productById.getPrice();
                int qty = billDetail.getQty();
                BigDecimal quantity = BigDecimalParser.parse(String.valueOf(qty));
                totalBill = totalBill.add(price.multiply(quantity));

                TxBillDetailEntity txBillDetail = Stream.of(new TxBillDetailEntity())
                        .peek(p -> {
                            p.setId(UUID.randomUUID().toString());
                            p.setTxBill(bill);
                            p.setProduct(productById);
                            p.setQuantity(billDetail.getQty());
                            p.setProductPrice(productById.getPrice());
                        }).findFirst().orElse(null);

                saveTxBillDetail(txBillDetail);

                BillDetailCreateResponse billDetailResponse = Stream.of(new BillDetailCreateResponse())
                                .peek(p -> {
                                    p.setId(txBillDetail.getId());
                                    p.setBillId(txBillDetail.getTxBill().getId());
                                    p.setProduct(productById);
                                    p.setProductPrice(productById.getPrice());
                                    p.setQuantity(billDetail.getQty());
                                }).findFirst().orElse(null);
                response.getTxBillDetail().add(billDetailResponse);
            }

            updateTotalBill(totalBill, bill.getId());

            response.setTotalBill(totalBill);
            return response;
        }

    // save transaction bill detail
    public void  saveTxBillDetail(TxBillDetailEntity payload){
        transactionRepository.saveTxBillDetail(payload.getId(),payload.getTxBill().getId(), payload.getProduct().getId(), payload.getQuantity(), payload.getProductPrice());
    }

    // update total bill
    public void updateTotalBill(BigDecimal totalBill, String id){
        transactionRepository.updateTotalBill(totalBill, id);
    }

    // Get list transaction
    public List<TransactionCreateResponse> getAll(){
        List<TransactionCreateResponse> billResponse = new ArrayList<>();
        List<TxBillEntity> bill = transactionRepository.findAll();

        for (TxBillEntity b : bill) {
            CustomerEntity customerById = findCustomerById(b.getCustomer().getId());
            EmployeeEntity employeeById = findEmployeeById(b.getEmployee().getId());

            TransactionCreateResponse response = Stream.of(new TransactionCreateResponse())
                    .peek(
                            p -> {
                                p.setId(b.getId());
                                p.setBillDate(b.getBill_date());
                                p.setEntryDate(b.getEntryDate());
                                p.setFinishDate(b.getFinishDate());
                                p.setCustomer(customerById);
                                p.setEmployee(employeeById);
                                p.setTotalBill(b.getTotalBill());
                            }).findFirst().orElse(null);

            List<BillDetailCreateResponse> billDetail = new ArrayList<>();
            List<TxBillDetailEntity> billDetails = getBillDetailsForBill(b.getId());

            for (TxBillDetailEntity bd : billDetails) {
                ProductEntity productById = findProductById(bd.getProduct().getId());
                BillDetailCreateResponse billDetailResponse = Stream.of(new BillDetailCreateResponse())
                        .peek(p -> {
                            p.setId(bd.getId());
                            p.setBillId(bd.getTxBill().getId());
                            p.setProduct(productById);
                            p.setProductPrice(productById.getPrice());
                            p.setQuantity(bd.getQuantity());
                        }).findFirst().orElse(null);

                billDetail.add(billDetailResponse);
            }

            response.setTxBillDetail(billDetail);
            billResponse.add(response);
        }

        return billResponse;
    }

    // find bill detail by bill id
    public List<TxBillDetailEntity> getBillDetailsForBill(String id){
        return transactionRepository.getBillDetailsForBill(id);
    }

    // find customer by id
    public CustomerEntity findCustomerById(String id){
        return transactionRepository.findCustomerById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    // find employee by id
    public EmployeeEntity findEmployeeById(String id){
        return transactionRepository.findEmployeeById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    // find product by id
    public ProductEntity findProductById(String id){
        return transactionRepository.findProductById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

}
