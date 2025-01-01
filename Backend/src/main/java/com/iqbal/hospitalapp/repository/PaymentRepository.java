package com.iqbal.hospitalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqbal.hospitalapp.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{

}
