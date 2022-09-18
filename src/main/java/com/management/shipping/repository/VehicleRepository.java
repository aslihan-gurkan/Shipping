package com.management.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.management.shipping.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

}
