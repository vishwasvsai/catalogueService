package com.kidzoo.catalogue.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kidzoo.catalogue.error.CatalogueError;
import com.kidzoo.catalogue.model.Toy;

@Component
public interface CatalogueRepository {
	public List<Toy> findAll() throws CatalogueError;
	public List<Toy> findByPrice(Double from, Double to) throws CatalogueError;
}
