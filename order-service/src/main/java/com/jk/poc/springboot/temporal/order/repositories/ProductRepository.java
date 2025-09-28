package com.jk.poc.springboot.temporal.order.repositories;

import com.jk.poc.springboot.temporal.order.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
