package com.management.shipping.service.impl;

import java.util.List;
import com.management.shipping.ddto.DeliveryPointDTO;
import com.management.shipping.ddto.DeliveryPointRequest;
import com.management.shipping.exception.ConstraintsViolationException;

public interface IDeliveryPointService {
	
	DeliveryPointDTO createDeliveryPoint(DeliveryPointRequest request) throws ConstraintsViolationException ; 
	
	List<DeliveryPointDTO> getDeliveryPoint();
	
}
