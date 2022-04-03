package com.kidzoo.catalogue.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kidzoo.catalogue.error.CatalogueError;
import com.kidzoo.catalogue.model.Toy;

@Component
public class CatalogueRepositoryStubImpl implements CatalogueRepository {
	static Logger LOG = LoggerFactory.getLogger(CatalogueRepositoryStubImpl.class);
	
	
	@Override
	public List<Toy> findAll() throws CatalogueError{
		ObjectMapper mapper = new ObjectMapper();
		List<Toy> alltoys=null;
		try {
			File resource = new ClassPathResource("data.json").getFile();
			alltoys = mapper.readValue(resource,new TypeReference<List<Toy>>(){});
		} catch (Exception e) {
			LOG.error("Error reading the json file ",e);
			throw new CatalogueError(e,"Error occured while reading data");
		}
		return alltoys;
	}

	@Override
	public List<Toy> findByPrice(Double from, Double to)throws CatalogueError {
		return filterByPrice(from,to,findAll());
	}
	
	public static List<Toy> filterByPrice(Double from, Double to,List<Toy> toys){
		List<Toy> result =toys.stream().filter(toy-> {
			if(from!=null && from>0) {
				return toy.getPrice()>=from;
			}else {
				return true;
			}
		}).filter(toy-> {
			if(to!=null && to>0) {
				return toy.getPrice()<=to;
			}else {
				return true;
			}
		}).collect(Collectors.toList());
		return result;
	}
}
