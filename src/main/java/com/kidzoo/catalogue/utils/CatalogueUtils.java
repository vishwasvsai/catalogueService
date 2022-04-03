package com.kidzoo.catalogue.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.kidzoo.catalogue.error.CatalogueError;
import com.kidzoo.catalogue.model.StockStatusEnum;
import com.kidzoo.catalogue.model.Toy;

public class CatalogueUtils {
	
	/**
	 * it parses price . The legal price formats are 
	 * 
	 * from-
	 * from-to
	 * -to
	 * from
	 * 
	 * Everything else is illegal
	 * 
	 * 
	 * @param price
	 * @return
	 * @throws CatalogueError
	 */
	public static  List<Double> parsePrice(String price) throws CatalogueError{
		List<Double> prices = new ArrayList<>(2);
		prices.add(0d);
		prices.add(0d);
		try {
			if(price!=null) {
				if(price.contains("-")) {
					String[]  inputPrices = price.split("-");
					if(inputPrices.length!=2) {
						if(inputPrices.length==1) {
							prices.set(0,Double.parseDouble(inputPrices[0]));
						}else {
							throw new CatalogueError(" Invalid input price format it should be <fromPrice>-<toPrice> or <fromPrice> or -<toPrice>");
						}
					}else if(StringUtils.hasLength(inputPrices[0])||StringUtils.hasLength(inputPrices[1]) ) {
						prices.set(0,StringUtils.hasLength(inputPrices[0])?Double.parseDouble(inputPrices[0]):0);
						prices.set(1,StringUtils.hasLength(inputPrices[1])?Double.parseDouble(inputPrices[1]):0);
					}else {
						throw new CatalogueError("Supply atleast one price");
					}
				}else {
					prices.set(0,Double.parseDouble(price));
				}
			}
		}catch(NumberFormatException e) {
			throw new CatalogueError(e,"Input Price should be Number");
		}
		
		return prices;
	}

	/**
	 * Validate the statuses it should be available , backorder or outofstock
	 * 
	 * @param stockStatuses
	 * @return
	 * @throws CatalogueError
	 */
	public static String validateAndExtract(String stockStatuses) throws CatalogueError{
		if(stockStatuses == null) {
			return StockStatusEnum.AVAILABLE.getStockStatus();
		}
		
		for(String status : stockStatuses.split(",")) {
			if(StockStatusEnum.valueOf(status) == null) {
				throw new CatalogueError(" Invalid Inventory status passed "+stockStatuses);
			}
		}
		return stockStatuses;
	}
	
	
	
	
	
	
}
