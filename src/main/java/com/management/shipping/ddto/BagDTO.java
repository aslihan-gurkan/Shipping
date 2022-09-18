package com.management.shipping.ddto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BagDTO {
	
	Long id;
	String barcode;
	Integer deliveryPointForUnloading;
	int status;
}
