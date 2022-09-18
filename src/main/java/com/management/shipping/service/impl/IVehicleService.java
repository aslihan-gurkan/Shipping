package com.management.shipping.service.impl;

import com.management.shipping.ddto.CreateVehicleRequest;
import com.management.shipping.exception.ConstraintsViolationException;

public interface IVehicleService {
	
	void createVehicle(CreateVehicleRequest request) throws ConstraintsViolationException;
}
