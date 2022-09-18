package com.management.shipping.Controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.management.shipping.ddto.CreatePackageRequest;
import com.management.shipping.ddto.PackageDTO;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.service.impl.IPackageService;

@RestController
@RequestMapping("/v1/packages")
public class PackageController {

	private final IPackageService packageService;
	
	@Autowired
	public PackageController(IPackageService packageService){
		this.packageService=packageService;
	}
	
	@PostMapping
	public ResponseEntity<PackageDTO> createPackage(@Valid @RequestBody CreatePackageRequest request) throws ConstraintsViolationException {
		PackageDTO response = packageService.createPackage(request);
		return ResponseEntity.ok(response);
	}
	
}
