package com.management.shipping.sservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.management.shipping.ddto.BagDTO;
import com.management.shipping.ddto.CreateBagRequest;
import com.management.shipping.ddto.PackagesBagsRequest;
import com.management.shipping.ddto.ShipmentStatus;
import com.management.shipping.dto.converter.BagConverter;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.model.Bag;
import com.management.shipping.model.Package;
import com.management.shipping.model.DeliveryPoint;
import com.management.shipping.repository.BagRepository;
import com.management.shipping.repository.DeliveryPointRepository;
import com.management.shipping.repository.PackageRepository;
import com.management.shipping.service.impl.IBagService;

@Service
public class BagService implements IBagService {

	private final BagRepository bagRepository;
	private final BagConverter bagConverter;
	private final DeliveryPointRepository deliveryPointRepository;
	private final PackageRepository packageRepository;
	
	@Autowired
	public BagService(BagRepository bagRepository,BagConverter bagConverter,
					  DeliveryPointRepository deliveryPointRepository, PackageRepository packageRepository) {
		this.bagRepository = bagRepository;
		this.bagConverter=bagConverter;
		this.deliveryPointRepository=deliveryPointRepository;
		this.packageRepository=packageRepository;
	}
	
	@Override
	public BagDTO createBag(CreateBagRequest request) throws ConstraintsViolationException {
		Bag bag = new Bag();
		String barcode = request.getBarcode();
		bag.setBarcode(barcode);
		Integer deliveryPointForUnloading = request.getDeliveryPointForUnloading();
		DeliveryPoint deliveryPoint = deliveryPointRepository.findByValue(deliveryPointForUnloading.intValue());
		bag.setDeliveryPoint(deliveryPoint);
		bag.setDeliveryPointForUnloading(deliveryPoint.getValue());
		bag.setStatus(ShipmentStatus.CREATED.getValue());

		Bag createdBag = bagRepository.save(bag);
		BagDTO createdBagDTO = bagConverter.convert(createdBag);
		return createdBagDTO;
	}
	
	@Override
	public void assignPackagesToBags(PackagesBagsRequest request) {
		
		String packageBarcode = request.getPackageBarcode();
		String bagBarcode = request.getBagBarcode();
		Bag bag = bagRepository.findByBarcode(bagBarcode);
		Package packageEntity = packageRepository.findByBarcode(packageBarcode);
		packageEntity.setStatus(ShipmentStatus.LOADEDINTOBAG.getValue());
		
		packageEntity.setBag(bag);
		packageRepository.save(packageEntity);	
	}

	void checkBagsIsInTable() {
		List<Bag> allBagss = bagRepository.findAll();

		Assert.notEmpty(
				allBagss, 
			      "There is no value in Bags table");	
	}
}
