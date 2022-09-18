package com.management.shipping.dto.converter;

import org.springframework.stereotype.Component;
import com.management.shipping.ddto.VehicleDTO;
import com.management.shipping.model.Vehicle;

@Component
public class VehicleConverter {

	public VehicleDTO convert(Vehicle vehicle) {
		VehicleDTO dto = new VehicleDTO();
		dto.setId(vehicle.getId());
		dto.setLicencePlate(vehicle.getLicencePlate());
		return dto;
	}
}
