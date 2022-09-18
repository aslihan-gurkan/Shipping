package com.management.shipping.ddto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PackagesBagsDTO {
	
	Integer id;
	String packageBarcode;
	String bagBarcode;
}
