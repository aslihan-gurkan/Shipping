package com.management.shipping.ddto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PackageDTO {
	
	Long id;
	String barcode;
	Integer deliveryPointForUnloading;
	Integer volumetricWeight;
	int status;
}
