package com.management.shipping.ddto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveriesResponse {
	String barcode;
	Integer state;
}
