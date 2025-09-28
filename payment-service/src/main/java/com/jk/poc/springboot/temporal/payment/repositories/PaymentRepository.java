package com.jk.poc.springboot.temporal.payment.repositories;

import com.jk.poc.springboot.temporal.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}