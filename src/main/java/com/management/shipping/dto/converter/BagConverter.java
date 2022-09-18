package com.management.shipping.dto.converter;

import org.springframework.stereotype.Component;
import com.management.shipping.ddto.BagDTO;
import com.management.shipping.model.Bag;

@Component
public class BagConverter {

	public BagDTO convert(Bag bag) {
		BagDTO dto = new BagDTO();
		dto.setId(bag.getId());
		dto.setDeliveryPointForUnloading(bag.getDeliveryPointForUnloading());
		dto.setBarcode(bag.getBarcode());
		dto.setStatus(bag.getStatus());
		return dto;
	}
}
