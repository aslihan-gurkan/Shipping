package com.management.shipping.service.impl;

import com.management.shipping.ddto.DistributionRequest;
import com.management.shipping.ddto.DistributionResponse;
import com.management.shipping.exception.ConstraintsViolationException;

public interface IDistributionService {
	
	DistributionResponse distribute(DistributionRequest request) throws ConstraintsViolationException;
}
