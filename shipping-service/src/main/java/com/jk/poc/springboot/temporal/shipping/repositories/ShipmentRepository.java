package com.jk.poc.springboot.temporal.shipping.repositories;

import com.jk.poc.springboot.temporal.shipping.models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String> {
}