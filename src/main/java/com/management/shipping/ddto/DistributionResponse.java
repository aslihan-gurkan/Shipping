package com.management.shipping.ddto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DistributionResponse {
	String plate;
	List<RouteResponse> route;
}
