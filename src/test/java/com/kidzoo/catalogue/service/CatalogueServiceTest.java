package com.kidzoo.catalogue.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kidzoo.catalogue.dao.CatalogueDAO;
import com.kidzoo.catalogue.dao.StockStatusDAO;
import com.kidzoo.catalogue.dto.ToyDto;
import com.kidzoo.catalogue.error.CatalogueError;
import com.kidzoo.catalogue.repository.CatalogueRepository;
import com.kidzoo.catalogue.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CatalogueService.class,CatalogueDAO.class,StockStatusDAO.class})
class CatalogueServiceTest {

	@MockBean
	private CatalogueRepository stubRepo;
	
	@Autowired
	private CatalogueService service;
	
	@Autowired
	private CatalogueDAO  catalogueDAO;
	
	// This will ignore stockStatus
	@MockBean
	StockStatusDAO stockdao;
	
	@Test
	public void getCataloguesTest() throws CatalogueError {
		ToyDto dto = new ToyDto(1000L,10.0,"Playing Card",1,"http://localhost:8080/PlayingCard.png");
		Mockito.when(stubRepo.findAll()).thenReturn(TestUtils.getToys());
		assertTrue(service.getCatalogues("available").size()==11);
		assertTrue(service.getCatalogues("available").get(0).equals(dto));
	}
	
	@Test
	public void getCataloguesTestByPrice() throws CatalogueError {
		Mockito.when(stubRepo.findByPrice(10d,30d)).thenReturn(TestUtils.getToys());
		assertTrue(service.getCatalogues(10d,30d,"available").size()==11);
	}
	
	
	@Test
	public void getCataloguesTestByPriceAndStockStatuc() throws CatalogueError {
		Mockito.when(stubRepo.findByPrice(10d,30d)).thenReturn(TestUtils.getToys());
		Mockito.when(stockdao.getInventoryByStatus(ArgumentMatchers.eq("backorder"))).thenReturn(TestUtils.getResponseByBackOrderStatus());
		assertTrue(service.getCatalogues(10d,30d,"backorder").size()==2);
	}
	
	
	@Test
	public void getCataloguesTestByStockStatuc() throws CatalogueError {
		Mockito.when(stubRepo.findAll()).thenReturn(TestUtils.getToys());
		Mockito.when(stockdao.getInventoryByStatus(ArgumentMatchers.eq("backorder"))).thenReturn(TestUtils.getResponseByBackOrderStatus());
		assertTrue(service.getCatalogues("backorder").size()==2);
	}
	
}
