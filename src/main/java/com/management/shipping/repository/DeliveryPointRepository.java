package com.management.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.management.shipping.model.DeliveryPoint;

public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, Long>{

    public DeliveryPoint findByValue(Integer value);

}
