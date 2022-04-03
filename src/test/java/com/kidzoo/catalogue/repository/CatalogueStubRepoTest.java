package com.kidzoo.catalogue.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.kidzoo.catalogue.error.CatalogueError;
import com.kidzoo.catalogue.utils.TestUtils;

class CatalogueStubRepoTest {

	@Test
	void filterByPrice() throws CatalogueError {
		assertTrue(CatalogueRepositoryStubImpl.filterByPrice(10d,30d, TestUtils.getToys()).size()==2);
		assertTrue(CatalogueRepositoryStubImpl.filterByPrice(10d,99d, TestUtils.getToys()).size()==5);
		
		assertTrue(CatalogueRepositoryStubImpl.filterByPrice(10d,0d, TestUtils.getToys()).size()==9);
		assertTrue(CatalogueRepositoryStubImpl.filterByPrice(10d,null, TestUtils.getToys()).size()==9);
		
		assertTrue(CatalogueRepositoryStubImpl.filterByPrice(0d,10d, TestUtils.getToys()).size()==3);
		assertTrue(CatalogueRepositoryStubImpl.filterByPrice(null,10d, TestUtils.getToys()).size()==3);
	}
}
