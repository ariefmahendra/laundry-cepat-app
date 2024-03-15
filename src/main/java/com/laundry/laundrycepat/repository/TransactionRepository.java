package com.laundry.laundrycepat.repository;

import com.laundry.laundrycepat.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TxBillEntity, String> {
    @Query("SELECT NEW EmployeeEntity(e.id, e.name, e.address, e.phoneNumber) FROM EmployeeEntity e WHERE e.id = ?1")
    Optional<EmployeeEntity> findEmployeeById(String id);

    @Query("SELECT NEW CustomerEntity(c.id, c.name, c.address, c.phoneNumber) FROM CustomerEntity c WHERE c.id = ?1")
    Optional<CustomerEntity> findCustomerById(String id);

    @Query("SELECT NEW ProductEntity (p.id, p.name, p.unit, p.price) FROM ProductEntity p WHERE p.id = ?1")
    Optional<ProductEntity> findProductById(String id);

    @Modifying
    @Query(value = "INSERT INTO tx_bill_details (id, bill_id, product_id, quantity, product_price) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void saveTxBillDetail(String id, String billId, String productId, int quantity, BigDecimal productPrice);

    @Modifying
    @Query(value = "UPDATE tx_bill SET total_bill = ?1 WHERE id = ?2", nativeQuery = true)
    void updateTotalBill(BigDecimal totalBill, String id);

    @Query("SELECT NEW TxBillDetailEntity(bd.id, bd.txBill, bd.product, bd.quantity, bd.productPrice) FROM TxBillDetailEntity bd WHERE bd.txBill.id = ?1")
    List<TxBillDetailEntity> getBillDetailsForBill(String id);
}
