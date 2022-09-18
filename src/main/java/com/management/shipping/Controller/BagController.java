package com.management.shipping.Controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.management.shipping.ddto.BagDTO;
import com.management.shipping.ddto.CreateBagRequest;
import com.management.shipping.ddto.PackagesBagsRequest;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.service.impl.IBagService;

@RestController
@RequestMapping("/v1/bags")
public class BagController {

	private final IBagService bagService;
	
	@Autowired
	public BagController(IBagService bagService){
		this.bagService=bagService;
	}
	
	@PostMapping
	public ResponseEntity<BagDTO> createBag(@Valid @RequestBody CreateBagRequest request) throws ConstraintsViolationException {
		BagDTO response = bagService.createBag(request);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/assign")
	public ResponseEntity<String> assignPackagesToBags(@RequestBody PackagesBagsRequest request) {
		bagService.assignPackagesToBags(request);
		return ResponseEntity.ok("Assigned");
	}
	
}
