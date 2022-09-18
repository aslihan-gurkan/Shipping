package com.management.shipping.service.impl;

import com.management.shipping.ddto.CreatePackageRequest;
import com.management.shipping.ddto.PackageDTO;
import com.management.shipping.exception.ConstraintsViolationException;

public interface IPackageService {
	
	PackageDTO createPackage(CreatePackageRequest request) throws ConstraintsViolationException ;
	
}
