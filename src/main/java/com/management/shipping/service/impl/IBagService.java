package com.management.shipping.service.impl;

import com.management.shipping.ddto.BagDTO;
import com.management.shipping.ddto.CreateBagRequest;
import com.management.shipping.ddto.PackagesBagsRequest;
import com.management.shipping.exception.ConstraintsViolationException;

public interface IBagService {

	BagDTO createBag(CreateBagRequest request) throws ConstraintsViolationException;
	
	void assignPackagesToBags(PackagesBagsRequest request);
}
