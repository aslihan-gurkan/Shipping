package com.management.shipping.sservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.management.shipping.ddto.DeliveriesRequest;
import com.management.shipping.ddto.DistributionRequest;
import com.management.shipping.ddto.DistributionResponse;
import com.management.shipping.ddto.RouteRequest;
import com.management.shipping.ddto.Tuple;
import com.management.shipping.dto.converter.DistributionResponseConverter;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.model.Bag;
import com.management.shipping.model.Package;
import com.management.shipping.repository.BagRepository;
import com.management.shipping.repository.PackageRepository;
import com.management.shipping.service.impl.IDistributionService;

@Service
public class DistributionService implements IDistributionService {

	private final PackageRepository packageRepository;
	private final BagRepository bagRepository;
	private final UnloadablityService unloadability;
	private final LogService logService;
	private final PackageService packageService;
	private final BagService bagService;
	private final DeliveryPointService deliveryPointService;
	private final DistributionResponseConverter distributionResponseConverter;
		
	@Autowired
	public DistributionService(PackageRepository packageRepository, DeliveryPointService deliveryPointService,
							   BagRepository bagRepository, DistributionResponseConverter distributionResponseConverter,
							   UnloadablityService unloadability, LogService logService,
							   PackageService packageService, BagService bagService) {
		this.packageRepository = packageRepository;
		this.bagRepository = bagRepository;
		this.distributionResponseConverter = distributionResponseConverter;
		this.unloadability = unloadability;
		this.deliveryPointService = deliveryPointService;
		this.logService = logService;
		this.packageService = packageService;
		this.bagService = bagService;
	}
	
	@Override
	public DistributionResponse distribute(DistributionRequest request) throws ConstraintsViolationException {
		List<RouteRequest> routes = request.getRoute();
		
		packageService.checkPackagesIsInTable();
		bagService.checkBagsIsInTable();
		deliveryPointService.checkDeliveryPointsIsInTable();
		
		//inner request transformation
		Map<String, List<Integer>> packageMeta = new HashMap();
		Map<String, List<Integer>> bagMeta = new HashMap();

		innerRequestTransformation(routes,packageMeta,bagMeta);
		
		//retrieve datas
		List<Package> retrievedPackages = retrievePackages(packageMeta);
		List<Bag> retrievedBags = retrieveBags(bagMeta);
		
		//distribution - main logic
		Map<String, Map<Integer, Boolean>> packageResult = 
				retrievedPackages
					.stream()
					.collect(Collectors.toMap(Package::getBarcode, item -> unloadability.checkUnloadability(item, packageMeta)));
		

		Map<String, Map<Integer, Boolean>> bagResult = 
				retrievedBags
					.stream()
					.collect(Collectors.toMap(Bag::getBarcode, item -> unloadability.checkUnloadability(item, bagMeta)));
		
		packageResult.putAll(bagResult);
		
		saveRetrievedShipments(retrievedPackages, retrievedBags);
		
		//incorrectly sent Delivery Point - Barcode pairs set to Log table
		setIncorrectShipmentBarcodes(packageResult);
		
		//conversion		
		return distributionResponseConverter.convert(request, packageResult);	
	}

	void saveRetrievedShipments(List<Package> retrievedPackages, List<Bag> retrievedBags) {
		packageRepository.saveAll(retrievedPackages);
		bagRepository.saveAll(retrievedBags);
	}
	
	void innerRequestTransformation(List<RouteRequest> routes,Map<String, List<Integer>> packageMeta, Map<String, List<Integer>> bagMeta) {
		for (RouteRequest route: routes) {
			ArrayList<DeliveriesRequest> deliveries = route.getDeliveries();
			Integer deliveryPoint = route.getDeliveryPoint();
			
			for (DeliveriesRequest delivery: deliveries) {
				String barcode = delivery.getBarcode();
				boolean isPackage = barcode.charAt(0) == 'P';
				Tuple<String, Integer> barcodePair = new Tuple(barcode, deliveryPoint);
				if (isPackage) {
					List<Integer> values = packageMeta.getOrDefault(barcode, new ArrayList());
					values.add(deliveryPoint);
					packageMeta.put(barcode, values);
				}
				else {
					List<Integer> values = bagMeta.getOrDefault(barcode, new ArrayList());
					values.add(deliveryPoint);
					bagMeta.put(barcode, values);				}
			}
		}
	}
	
	List<Package> retrievePackages(Map<String, List<Integer>> packageMeta) {
		List<Package> retrievedPackages = packageRepository.findByBarcodeIn(new ArrayList<>(packageMeta.keySet()));
		return retrievedPackages;
	}
	
	List<Bag> retrieveBags(Map<String, List<Integer>> bagMeta) {

		List<Bag> retrievedBags = bagRepository.findByBarcodeIn(new ArrayList<>(bagMeta.keySet()));
		return retrievedBags;
	}

	void setIncorrectShipmentBarcodes(Map<String, Map<Integer, Boolean>> packageResult) {
		logService.errorLogSave(packageResult);	
	}
}
