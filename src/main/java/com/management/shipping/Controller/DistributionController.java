package com.management.shipping.Controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.management.shipping.ddto.DistributionRequest;
import com.management.shipping.ddto.DistributionResponse;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.service.impl.IDistributionService;

@RestController
@RequestMapping("/v1/distribution")
public class DistributionController {

	private final IDistributionService distributionService;
	
	@Autowired
	public DistributionController(IDistributionService distributionService){
		this.distributionService=distributionService;
	}
	
	@PostMapping("/initiate")
	public ResponseEntity<DistributionResponse> createVehicle(@Valid @RequestBody DistributionRequest request) throws ConstraintsViolationException {
		DistributionResponse response = distributionService.distribute(request);
		return ResponseEntity.ok(response);
	}
	
}
