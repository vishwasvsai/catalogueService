package com.kidzoo.catalogue.dto;

import com.kidzoo.catalogue.model.Toy;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ToyDto {
	@NonNull private Long id;
	@NonNull private Double price;
	@NonNull private String name;
	@NonNull private Integer age;
	@NonNull private String url;
	
	public ToyDto(Toy toy) {
		this.id=toy.getId();
		this.price=toy.getPrice();
		this.name=toy.getName();
		this.url=toy.getUrl();
		this.age=toy.getAge();
	}
}
