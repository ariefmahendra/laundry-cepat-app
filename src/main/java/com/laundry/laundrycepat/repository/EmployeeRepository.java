package com.laundry.laundrycepat.repository;

import com.laundry.laundrycepat.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    Optional<EmployeeEntity> findByPhoneNumber(String phoneNumber);
}
