package com.management.shipping.sservice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.management.shipping.model.ErrorLog;
import com.management.shipping.repository.LogRepository;

@Component
public class LogService {
	
	private final LogRepository logRepository;

	@Autowired
	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}
	
	void errorLogSave(Map<String, Map<Integer, Boolean>> packageResult) {
		List<ErrorLog> errorLogsToSave = packageResult.entrySet()
					 .stream()
					 .flatMap((k) -> 
					 	k.getValue()
					 	  .entrySet()
					 	  .stream()
					 	  .filter((t) -> !t.getValue())
					 	  .map((t) -> new ErrorLog(t.getKey(), k.getKey()))
					).collect(Collectors.toList());
					
		logRepository.saveAll(errorLogsToSave);
	}
	
}
