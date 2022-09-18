package com.management.shipping.ddto;

import java.util.ArrayList;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouteRequest {

	@NotNull
	Integer deliveryPoint;
	@NotEmpty
	ArrayList<DeliveriesRequest> deliveries;
	
}
