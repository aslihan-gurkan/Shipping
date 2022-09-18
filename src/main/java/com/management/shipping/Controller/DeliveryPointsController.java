package com.management.shipping.Controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.management.shipping.ddto.DeliveryPointDTO;
import com.management.shipping.ddto.DeliveryPointRequest;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.service.impl.IDeliveryPointService;

@RestController
@RequestMapping("/v1/delivery_points")
public class DeliveryPointsController {

	private final IDeliveryPointService deliveryPointService;
	
	@Autowired
	public DeliveryPointsController(IDeliveryPointService deliveryPointService){
		this.deliveryPointService=deliveryPointService;
	}
	
	@PostMapping
	public ResponseEntity<DeliveryPointDTO> createDeliveryPoint(@Valid @RequestBody DeliveryPointRequest request) throws ConstraintsViolationException {
		DeliveryPointDTO response = deliveryPointService.createDeliveryPoint(request);
		return ResponseEntity.ok(response);
	}
	

	@GetMapping("/deliveryPoints")
	public ResponseEntity<List<DeliveryPointDTO>> getDeliveryPoint() {
		List<DeliveryPointDTO> response = deliveryPointService.getDeliveryPoint();
		return ResponseEntity.ok(response);
	}
}
