package com.management.shipping.ddto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouteResponse {
	Integer deliveryPoint;
	List<DeliveriesResponse> deliveries;
}
