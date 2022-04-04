package com.kidzoo.catalogue.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.kidzoo.catalogue.error.CatalogueError;

class CatalogueUtilsTest {

	@Test
	void parsePrice() throws CatalogueError {
		assertTrue(Double.compare(CatalogueUtils.parsePrice("100").get(0), 100d) == 0);
		assertTrue(Double.compare(CatalogueUtils.parsePrice("100-200").get(0), 100d) == 0);
		assertTrue(Double.compare(CatalogueUtils.parsePrice("100-200").get(1), 200d) == 0);
		assertTrue(Double.compare(CatalogueUtils.parsePrice("100-").get(0), 100d) == 0);
		assertTrue(Double.compare(CatalogueUtils.parsePrice("100-").get(1), 0d) == 0);
		assertTrue(Double.compare(CatalogueUtils.parsePrice("-200").get(1), 200d) == 0);
		assertTrue(Double.compare(CatalogueUtils.parsePrice("-200").get(0), 0d) == 0);
		
		
		 Exception numberformat = assertThrows(CatalogueError.class, () -> {
			 CatalogueUtils.parsePrice("1a");
		 });
		 
		 Exception invalidformat = assertThrows(CatalogueError.class, () -> {
			 CatalogueUtils.parsePrice("-");
		 });

		 assertTrue(numberformat.getMessage().contains("Price should be Number"));
		 assertTrue(invalidformat.getMessage().contains("Invalid input price format"));
	}
	
	
	@Test
	void validateStockStatus() throws CatalogueError {
		assertTrue(CatalogueUtils.validateStockStatus(null).equals("available"));
		assertTrue(CatalogueUtils.validateStockStatus("available").equals("available"));
		assertTrue(CatalogueUtils.validateStockStatus("available,backorder").equals("available,backorder"));
		
		Exception invalidformat = assertThrows(CatalogueError.class, () -> {
			CatalogueUtils.validateStockStatus("notavilable");
		 });
		
		assertTrue(invalidformat.getMessage().contains("Invalid Inventory status passed "));
	}
	
	

}
