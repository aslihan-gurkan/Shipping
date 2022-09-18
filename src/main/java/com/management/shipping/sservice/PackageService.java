package com.management.shipping.sservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.management.shipping.ddto.CreatePackageRequest;
import com.management.shipping.ddto.PackageDTO;
import com.management.shipping.ddto.ShipmentStatus;
import com.management.shipping.model.Package;
import com.management.shipping.dto.converter.PackageConverter;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.model.DeliveryPoint;
import com.management.shipping.repository.DeliveryPointRepository;
import com.management.shipping.repository.PackageRepository;
import com.management.shipping.service.impl.IPackageService;
@Service
public class PackageService implements IPackageService {

	private final PackageRepository packageRepository;
	private final DeliveryPointRepository deliveryPointRepository;
	private final PackageConverter packageConverter;
	
	@Autowired
	public PackageService(PackageRepository packageRepository,PackageConverter packageConverter, 
						  DeliveryPointRepository deliveryPointRepository) {
		this.packageRepository = packageRepository;
		this.packageConverter=packageConverter;
		this.deliveryPointRepository=deliveryPointRepository;
	}
	
	@Override
	public PackageDTO createPackage(CreatePackageRequest request) throws ConstraintsViolationException {
		Package packageEntity = new Package();
		String barcode = request.getBarcode();
		packageEntity.setBarcode(barcode);
		Integer deliveryPointForUnloading = request.getDeliveryPointForUnloading();
		DeliveryPoint deliveryPoint = deliveryPointRepository.findByValue(deliveryPointForUnloading.intValue());
		packageEntity.setDeliveryPoint(deliveryPoint);
		packageEntity.setDeliveryPointForUnloading(deliveryPoint.getValue());
		packageEntity.setVolumetricWeight(request.getVolumetricWeight());
		packageEntity.setStatus(ShipmentStatus.CREATED.getValue());
		
		Package createdPackage= packageRepository.save(packageEntity);
		PackageDTO createdPackageDTO = packageConverter.convert(createdPackage);
		return createdPackageDTO;
	}
	
	void checkPackagesIsInTable() {
		List<Package> allPackages = packageRepository.findAll();

		Assert.notEmpty(
				allPackages, 
			      "There is no value in Packages table");	
	}
}
