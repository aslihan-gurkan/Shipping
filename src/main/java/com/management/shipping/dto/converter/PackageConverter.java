package com.management.shipping.dto.converter;

import org.springframework.stereotype.Component;
import com.management.shipping.ddto.PackageDTO;
import com.management.shipping.model.Package;

@Component
public class PackageConverter {

	public PackageDTO convert(Package packageEntity) {
		PackageDTO dto = new PackageDTO();
		dto.setId(packageEntity.getId());
		dto.setBarcode(packageEntity.getBarcode());
		dto.setDeliveryPointForUnloading(packageEntity.getDeliveryPointForUnloading());
		dto.setVolumetricWeight(packageEntity.getVolumetricWeight());
		dto.setStatus(packageEntity.getStatus());
		return dto;
	}
}
