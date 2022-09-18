package com.management.shipping.ddto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateVehicleRequest {

	public CreateVehicleRequest(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	@NotBlank
	String licencePlate;
}
