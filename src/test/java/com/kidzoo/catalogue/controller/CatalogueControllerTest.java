package com.kidzoo.catalogue.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.kidzoo.catalogue.dao.StockStatusDAO;
import com.kidzoo.catalogue.utils.TestUtils;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CatalogueControllerTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	// This will ignore stockStatus
	@MockBean
	public StockStatusDAO stockdao;
	
	@Test
	public void getCatalogues_v1_0() {
		assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1.0/catalogue",String.class).contains("towerofhanoi"));
	}
	
	@Test
	public void getCataloguesByPrice_v1_0() {
		assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1.0/catalogue?price=130-150",String.class).contains("Remote Control Car"));
	}
	
	@Test
	public void getCataloguesByStockStatus_v1_0() {
		Mockito.when(stockdao.getInventoryByStatus(ArgumentMatchers.eq("backorder"))).thenReturn(TestUtils.getResponseByBackOrderStatus());
		assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1.0/catalogue?price=5-10&stockstatus=backorder",String.class).contains("Playing Card"));
	}
}
