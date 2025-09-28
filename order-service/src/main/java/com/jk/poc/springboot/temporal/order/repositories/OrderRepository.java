package com.jk.poc.springboot.temporal.order.repositories;

import com.jk.poc.springboot.temporal.order.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
