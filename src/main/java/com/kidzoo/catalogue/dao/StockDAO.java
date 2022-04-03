package com.kidzoo.catalogue.dao;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kidzoo.catalogue.dto.Inventory;


@Component
public class StockDAO {
	
	@Autowired
	public RestTemplate restTemplate;
	static Logger LOG = LoggerFactory.getLogger(StockDAO.class);
	public static String BASE_URL="http://inventory.kidzoo.com/v2/inventory/";
	
	/**
	 * Return toy by Inventory Id
	 * 
	 * @param id
	 * @return
	 */
	public Inventory getInventoryById(Long id) {
		Inventory result =null;
		try {
			ResponseEntity<String> response   = restTemplate.getForEntity(BASE_URL+id, String.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				result = mapper.readValue(response.getBody(),new TypeReference<Inventory>(){});
				
			}
		} catch (Exception e) {
			logError(e);
		}
		return result;
	}
	
	/**
	 * Take list of comma separated status Example "available,backorder"
	 * 
	 * @param status
	 * @return
	 */
	public Set<Inventory> getInventoryByStatus(String status) {
		Set<Inventory> result =null;
		try {
			ResponseEntity<String> response   = restTemplate.getForEntity(BASE_URL+"findByStatus?status="+status, String.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				result = mapper.readValue(response.getBody(),new TypeReference<Set<Inventory>>(){});
			}
		} catch (Exception e) {
			logError(e);
		}
		return result;
	}

	private void logError(Exception e) {
		LOG.error(" Exception while converting Inventory json");
	}
}
