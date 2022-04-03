package com.kidzoo.catalogue.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Toy {
	
	@Getter @Setter private Long id;
	@Getter @Setter private Double price;
	@Getter @Setter private String name;
	@Getter @Setter private Integer age;
	@Getter @Setter private String url;
	
	public Toy(Long id, Double price, String name, Integer age, String url) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
		this.age = age;
		this.url = url;
	}

	public Toy() {
		super();
	}
	
	
}
