package com.management.shipping.dto.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.management.shipping.ddto.DeliveriesRequest;
import com.management.shipping.ddto.DeliveriesResponse;
import com.management.shipping.ddto.DistributionRequest;
import com.management.shipping.ddto.DistributionResponse;
import com.management.shipping.ddto.RouteRequest;
import com.management.shipping.ddto.RouteResponse;
import com.management.shipping.ddto.ShipmentStatus;

@Component
public class DistributionResponseConverter {
	
	
	RouteResponse convertRoute(RouteRequest request, Map<String, Map<Integer, Boolean>> unloadResult) {
		RouteResponse response = new RouteResponse();
	
		Integer deliveryPoint = request.getDeliveryPoint();
		
		List<DeliveriesResponse> deliveryResponse = 
				request.getDeliveries()
						.stream()
						.map((DeliveriesRequest deliveryRequest) -> convertDelivery(deliveryRequest, deliveryPoint, unloadResult))
						.collect(Collectors.toList());
		response.setDeliveryPoint(deliveryPoint);
		response.setDeliveries(deliveryResponse);
		return response;
	}
	
	
	DeliveriesResponse convertDelivery(DeliveriesRequest request, Integer deliveryPoint, Map<String, Map<Integer, Boolean>> unloadResult) {
		DeliveriesResponse response = new DeliveriesResponse();
		
		String barcode = request.getBarcode();
		System.out.println(barcode);
		ShipmentStatus state = unloadResult.getOrDefault(barcode, new HashMap<Integer, Boolean>()).getOrDefault(deliveryPoint, false) ? ShipmentStatus.UNLOADED : ShipmentStatus.LOADED;  // Unloaded/Loaded
		
		response.setBarcode(request.getBarcode());
		response.setState(state.getValue());
		
		return response;
	}
	
	
	
	public DistributionResponse convert(DistributionRequest request, Map<String, Map<Integer, Boolean>> unloadResult) {
		
		DistributionResponse response = new DistributionResponse();
		
		List<RouteResponse> responseRoute = 
				request.getRoute()
					   .stream()
					   .map((RouteRequest route) -> convertRoute(route, unloadResult))
					   .collect(Collectors.toList());
		
		response.setPlate(request.getPlate());
		response.setRoute(responseRoute);
		
		return response;
	}
}
