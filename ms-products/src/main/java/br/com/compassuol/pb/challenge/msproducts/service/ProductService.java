package br.com.compassuol.pb.challenge.msproducts.service;

import java.util.List;


import br.com.compassuol.pb.challenge.msproducts.payload.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto);
	
	List<ProductDto> getAllProducts();
	
	ProductDto getProductById(long id);
	
	ProductDto updateProduct(ProductDto productDto, long id);
	
	void deleteProductById(long id);
}
