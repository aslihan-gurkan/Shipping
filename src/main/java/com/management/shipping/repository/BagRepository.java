package com.management.shipping.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.management.shipping.model.Bag;

public interface BagRepository extends JpaRepository<Bag, Long>{

	public Bag findByBarcode(String barcode);
	
	List<Bag> findByBarcodeIn(List<String> barcodes);	
}
