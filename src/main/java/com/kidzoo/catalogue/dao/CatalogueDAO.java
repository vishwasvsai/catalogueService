package com.kidzoo.catalogue.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kidzoo.catalogue.dto.ToyDto;
import com.kidzoo.catalogue.error.CatalogueError;
import com.kidzoo.catalogue.model.Toy;
import com.kidzoo.catalogue.repository.CatalogueRepository;

@Component
public class CatalogueDAO {
	@Autowired
	private CatalogueRepository  catalogueRepository;
	
	public List<ToyDto> getCatalogues() throws CatalogueError{
		return catalogueRepository.findAll().stream().map(toy -> new ToyDto(toy)).collect(Collectors.toList());
	} 
	
	
	public List<ToyDto> getCatalogues(Double fromPrice, Double toPrice)throws CatalogueError {
		return catalogueRepository.findByPrice(fromPrice, toPrice).stream().map(toy -> new ToyDto(toy)).collect(Collectors.toList());
	}
}
