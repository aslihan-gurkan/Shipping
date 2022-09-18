package com.management.shipping.ddto;

import java.util.ArrayList;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DistributionRequest {

	@NotBlank
	String plate;
	@NotNull
	ArrayList<RouteRequest> route;
	
}
