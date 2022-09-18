package com.management.shipping.dto.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.management.shipping.ddto.DeliveryPointDTO;
import com.management.shipping.model.DeliveryPoint;

@Component
public class DeliveryPointConverter {

	public DeliveryPointDTO convert(DeliveryPoint deliveryPoint) {
		DeliveryPointDTO dto = new DeliveryPointDTO();
		dto.setId(deliveryPoint.getId());
		dto.setDeliveryPointName(deliveryPoint.getDeliveryPoint());
		dto.setValue(deliveryPoint.getValue());
		return dto;
	}
	
	public List<DeliveryPointDTO> convert(List<DeliveryPoint> deliveryPoints) {
		
		List<DeliveryPointDTO> dtoList = new ArrayList<DeliveryPointDTO>();
		DeliveryPointDTO dto = new DeliveryPointDTO();
		
		for(DeliveryPoint deliveryPoint : deliveryPoints)
		{
			dto.setId(deliveryPoint.getId());
			dto.setDeliveryPointName(deliveryPoint.getDeliveryPoint());
			dto.setValue(deliveryPoint.getValue());
		}
		dtoList.add(dto);
		return dtoList;
		
	}
}
