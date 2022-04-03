package com.kidzoo.catalogue.utils;

import java.io.File;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
}
