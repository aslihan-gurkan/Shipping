package com.management.shipping.sservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.management.shipping.model.Package;
import com.management.shipping.model.Bag;

@Service
public class UnloadablityService {
	
	boolean isUnloadable(boolean isRelatedToBag, Integer deliveryPoint) {
		boolean result = false;
		if (deliveryPoint == 1) {
			result = !isRelatedToBag;
		}
		else if (deliveryPoint == 2) {
			result = true;
		}
		else if (deliveryPoint == 3) {
			result = isRelatedToBag;
		}
		return result;
	}
	
	Map<Integer, Boolean> checkUnloadability(Package pkg, Map<String, List<Integer>> packageMeta) {
		boolean isRelatedToBag = pkg.getBag() != null;
		Map<Integer, Boolean>  loadabilityResult = checkUnloadability(isRelatedToBag, packageMeta.getOrDefault(pkg.getBarcode(), new ArrayList()) ,pkg.getDeliveryPoint().getValue());
		int status = loadabilityResult.values().contains(true) ? 4 : 3;
		pkg.setStatus(status);
		return loadabilityResult;
	}
	
	Map<Integer, Boolean> checkUnloadability(Bag bag, Map<String, List<Integer>> bagMeta) {
		boolean isRelatedToBag = true;
		Map<Integer, Boolean>  loadabilityResult = checkUnloadability(isRelatedToBag, bagMeta.getOrDefault(bag.getBarcode(), new ArrayList()) ,bag.getDeliveryPoint().getValue());
		int status = loadabilityResult.values().contains(true) ? 4 : 3;
		bag.setStatus(status);
		Set<Package> packages = bag.getPackages();
		if(packages != null) {
			packages.forEach((Package pkg) -> {
				if (status == 4) { 
					pkg.setStatus(status);
				}								
			}
			);
		}
		return loadabilityResult;
	}
	
	Map<Integer, Boolean> checkUnloadability(boolean isRelatedToBag, List<Integer> intendedDeliveryPoints, Integer deliveryPoint) {
		Map<Integer, Boolean> result = new HashMap();
		boolean isUnloaded = false;
		for (Integer point: intendedDeliveryPoints) {
			if (isUnloaded) {
				result.put(point, false);
				continue;
			}
			boolean isUnloadableToPoint = this.isUnloadable(isRelatedToBag, point);
			boolean unloadabilityTest = (deliveryPoint == point) && isUnloadableToPoint;
			result.put(point, unloadabilityTest);
			isUnloaded = unloadabilityTest;
			
		}
		return result;
	}

}
