package com.management.shipping.sservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.management.shipping.ddto.CreateVehicleRequest;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.model.Vehicle;
import com.management.shipping.repository.VehicleRepository;
import com.management.shipping.service.impl.IVehicleService;

@Service
public class VehicleService implements IVehicleService {

	private final VehicleRepository vehicleRepository;
	
	@Autowired
	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	@Override
	public void createVehicle(CreateVehicleRequest request) throws ConstraintsViolationException {
		Vehicle vehicle = new Vehicle();
		String licencePlate = request.getLicencePlate();
		vehicle.setLicencePlate(licencePlate);
		
		vehicleRepository.save(vehicle);        
	}
}
