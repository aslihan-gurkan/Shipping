package com.management.shipping.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.management.shipping.model.Bag;
import com.management.shipping.model.Package;

public interface PackageRepository extends JpaRepository<Package, Long>{

	public Package findByBarcode(String barcode);
	
	List<Package> findByBag(Bag bag, Sort sort);
	
	List<Package> findByBarcodeIn(List<String> barcodes);
}
