package com.management.shipping.ddto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBagRequest {

	@NotBlank
	String barcode;
	@NotNull
	Integer deliveryPointForUnloading;
}
