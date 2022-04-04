package com.kidzoo.catalogue.utils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kidzoo.catalogue.dto.Inventory;
import com.kidzoo.catalogue.model.Toy;

public class TestUtils {
	public static  List<Toy> getToys() {
		ObjectMapper mapper = new ObjectMapper();
		List<Toy> alltoys=null;
		try {
			File resource = new ClassPathResource("data.json").getFile();
			alltoys = mapper.readValue(resource,new TypeReference<List<Toy>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alltoys;
	}
	
	public static ResponseEntity<String> getResponseforId(Long id){
		return  new ResponseEntity<>(
		          "{\"id\":"+id+", \"status\":\"available\"}", HttpStatus.OK);
		
	}
	
	public static  ResponseEntity<String> getResponseByStatus(){
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
	
	public static  Set<Inventory>  getResponseByBackOrderStatus(){
		Set<Inventory> backorder=new HashSet<>();
		backorder.add(new Inventory(1000L,"backorder")); 
		backorder.add(new Inventory(1001L,"backorder"));
		return  backorder;
	}
}
