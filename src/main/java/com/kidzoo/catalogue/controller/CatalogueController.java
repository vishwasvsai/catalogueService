package com.kidzoo.catalogue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kidzoo.catalogue.dto.ToyDto;
import com.kidzoo.catalogue.error.CatalogueError;
import com.kidzoo.catalogue.response.CatalogueResponse;
import com.kidzoo.catalogue.service.CatalogueService;
import com.kidzoo.catalogue.utils.CatalogueUtils;

@RestController
public class CatalogueController {
	@Autowired
	private CatalogueService catalogueService;
	
	@GetMapping("/v1.0/catalogue")
	public ResponseEntity<CatalogueResponse> getCatalogues(@RequestParam(name="price", required = false) String price,
			@RequestParam(name="stockstatus", required = false) String stockstatus) {
		List<ToyDto> responseData;
		try {
			String stockStatus = CatalogueUtils.validateAndExtract(stockstatus);
			if(price!=null) {
				List<Double> prices =CatalogueUtils.parsePrice(price);
				responseData = catalogueService.getCatalogues(prices.get(0),prices.get(1),stockStatus);
			}else {
				responseData = catalogueService.getCatalogues(stockStatus);
			}
		} catch (CatalogueError e) {
			return new ResponseEntity<CatalogueResponse>(new CatalogueResponse(e.getMessage()),HttpStatus.BAD_REQUEST); 
		}
		return new ResponseEntity<CatalogueResponse>(new CatalogueResponse(responseData),HttpStatus.ACCEPTED); 
	}
}
