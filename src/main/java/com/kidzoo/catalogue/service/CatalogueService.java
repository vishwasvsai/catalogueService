package com.kidzoo.catalogue.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kidzoo.catalogue.dao.CatalogueDAO;
import com.kidzoo.catalogue.dao.StockDAO;
import com.kidzoo.catalogue.dto.Inventory;
import com.kidzoo.catalogue.dto.ToyDto;
import com.kidzoo.catalogue.error.CatalogueError;

@Component
public class CatalogueService {
	static Logger LOG = LoggerFactory.getLogger(CatalogueService.class);
	// bypass stock as we dont have api ready yet
	private boolean ignoreStock=true;
	
	@Autowired
	private CatalogueDAO  catalogueDAO;
	
	
	@Autowired
	private StockDAO  stockDAO;
	
	public List<ToyDto> getCatalogues(String stockStatus) throws CatalogueError{
		return filterByStockStatus(catalogueDAO.getCatalogues(),stockStatus);
	}
	
	public List<ToyDto> getCatalogues(Double fromPrice,Double toPrice,String stockStatus) throws CatalogueError{
		return filterByStockStatus(catalogueDAO.getCatalogues(fromPrice,toPrice),stockStatus); 
	}
	
	
	public  List<ToyDto> filterByStockStatus(List<ToyDto> toys,String stockStatus){
		Set<Inventory> inventoryAsked = stockDAO.getInventoryByStatus(stockStatus);
		if(inventoryAsked!=null && inventoryAsked.size()>0) {
			return getToysByInventory(toys,inventoryAsked);
		}
		
		if(ignoreStock) {
			LOG.warn(" Ignoring the stock status because api not available yet");
			return toys;
		}
		return null;
	}
	
	public List<ToyDto>  getToysByInventory(List<ToyDto> toys,Set<Inventory> inventoryAsked ){
		List<ToyDto> filteredToys = new ArrayList<>();
		Set<Long> inventoryIds = inventoryAsked.stream().map(x->x.getId()).collect(Collectors.toSet());
		for(ToyDto toy:toys) {
			if(inventoryIds.contains(toy.getId())) {
				filteredToys.add(toy);
			}
		}
		return filteredToys;
	}

	public boolean isIgnoreStock() {
		return ignoreStock;
	}

	public void setIgnoreStock(boolean ignoreStock) {
		this.ignoreStock = ignoreStock;
	}
}
