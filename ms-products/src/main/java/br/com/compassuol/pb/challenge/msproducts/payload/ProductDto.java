package br.com.compassuol.pb.challenge.msproducts.payload;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {

	private Date date;

	@NotEmpty
	@Size(min = 10, message = "Product description should have at least 10 characters")
	private String description;
	
	@NotEmpty
	@Size(min = 2, message = "Product name should have at least 2 characters")
	private String name;
	
	private String imgUrl;
	@NotEmpty
	private double price;
}
