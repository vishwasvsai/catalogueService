package com.kidzoo.catalogue.response;

import java.util.List;

import com.kidzoo.catalogue.dto.ToyDto;

import lombok.Getter;


public class CatalogueResponse {
	@Getter private List<ToyDto> data;
	@Getter private String message;
	
	
	public CatalogueResponse(List<ToyDto> data) {
		super();
		this.data = data;
	}
	
	public CatalogueResponse(String message) {
		super();
		this.message = message;
	}
	
}
