package com.kidzoo.catalogue.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.kidzoo.catalogue.dto.Inventory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {StockStatusDAO.class})

class StockDAOTest {
	
	@MockBean
	public RestTemplate restTemplate;
	
	@Autowired
	StockStatusDAO stockDao;

	
	@Test
	public void getInventoryByIdTest(){
		Long id = 1000L;
		Mockito.when(restTemplate.getForEntity(ArgumentMatchers.eq(StockStatusDAO.BASE_URL+id), ArgumentMatchers.eq(String.class))).thenReturn(getResponseforId(id));
		
		Inventory inventory = stockDao.getInventoryById(id);
		assertTrue(inventory.getId().equals(id));
	}
	
	
	@Test
	public void getInventoryStatusTest(){
		Mockito.when(restTemplate.getForEntity(ArgumentMatchers.eq(StockStatusDAO.BASE_URL+"findByStatus?status=available"), ArgumentMatchers.eq(String.class))).thenReturn(getResponseByStatus());
		
		Set<Inventory> inventory = stockDao.getInventoryByStatus("available");
		assertTrue(inventory.size() ==12);
	}
	
	
	public ResponseEntity<String> getResponseforId(Long id){
		return  new ResponseEntity<>(
		          "{\"id\":"+id+", \"status\":\"available\"}", HttpStatus.OK);
		
	}
	
	public ResponseEntity<String> getResponseByStatus(){
		return  new ResponseEntity<>(
					"[{\"id\":1000, \"status\":\"available\"},"
		          		+ "{\"id\":1001, \"status\":\"available\"},"
		          		+ "{\"id\":1002, \"status\":\"available\"},"
		          		+ "{\"id\":1003, \"status\":\"available\"},"
		          		+ "{\"id\":1004, \"status\":\"available\"},"
		          		+ "{\"id\":1005, \"status\":\"available\"},"
		          		+ "{\"id\":1006, \"status\":\"available\"},"
		          		+ "{\"id\":1007, \"status\":\"available\"},"
		          		+ "{\"id\":1008, \"status\":\"available\"},"
		          		+ "{\"id\":1009, \"status\":\"available\"},"
		          		+ "{\"id\":1010, \"status\":\"available\"},"
		          		+ "{\"id\":1011, \"status\":\"available\"}]"
		          		, HttpStatus.OK);
		
	}
}
