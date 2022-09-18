package com.management.shipping.sservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.management.shipping.ddto.DeliveryPointDTO;
import com.management.shipping.ddto.DeliveryPointRequest;
import com.management.shipping.dto.converter.DeliveryPointConverter;
import com.management.shipping.exception.ConstraintsViolationException;
import com.management.shipping.model.DeliveryPoint;
import com.management.shipping.repository.DeliveryPointRepository;
import com.management.shipping.service.impl.IDeliveryPointService;


@Service
public class DeliveryPointService implements IDeliveryPointService {

	private final DeliveryPointRepository deliveryPointRepository;
	private final DeliveryPointConverter deliveryPointConverter;
	
	@Autowired
	public DeliveryPointService(DeliveryPointRepository deliveryPointRepository, DeliveryPointConverter deliveryPointConverter) {
		this.deliveryPointRepository = deliveryPointRepository;
		this.deliveryPointConverter=deliveryPointConverter;
	}
	
	@Override
	public DeliveryPointDTO createDeliveryPoint(DeliveryPointRequest request) throws ConstraintsViolationException {
		DeliveryPoint deliveryPoint = new DeliveryPoint();
		deliveryPoint.setDeliveryPoint(request.getDeliveryPoint());
		deliveryPoint.setValue(request.getValue());	

		DeliveryPoint createdDeliveryPoint = deliveryPointRepository.save(deliveryPoint);
		DeliveryPointDTO createdDeliveryPointDTO = deliveryPointConverter.convert(createdDeliveryPoint);
		return createdDeliveryPointDTO;
	}
	
	public List<DeliveryPointDTO> getDeliveryPoint() {
		List<DeliveryPoint> deliveryPoints = deliveryPointRepository.findAll();
		List<DeliveryPointDTO> dtoList = deliveryPointConverter.convert(deliveryPoints);
		return dtoList;
	}

	void checkDeliveryPointsIsInTable() {
		List<DeliveryPoint> allDeliveryPoints = deliveryPointRepository.findAll();

		Assert.notEmpty(
				allDeliveryPoints, 
			      "There is no value in DeliveryPoints table");	
	}
}
