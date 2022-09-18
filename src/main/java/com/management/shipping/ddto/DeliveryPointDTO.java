package com.management.shipping.ddto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryPointDTO {
	
	Long id;
	String deliveryPointName;
	Integer value;

}
