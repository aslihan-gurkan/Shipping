package com.management.shipping.Controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.management.shipping.ddto.CreateVehicleRequest;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.service.impl.IVehicleService;

@RestController
@RequestMapping("/v1/vehicles")
public class VehiclesController {

	private final IVehicleService vehicleService;
	
	@Autowired
	public VehiclesController(IVehicleService vehicleService){
		this.vehicleService=vehicleService;
	}
	
	@PostMapping
	public ResponseEntity<Object> createVehicle(@Valid @RequestBody CreateVehicleRequest request) throws ConstraintsViolationException {
		vehicleService.createVehicle(request);
		return new ResponseEntity<>("Vehicle is created successfully", HttpStatus.CREATED);
	}
	
}
