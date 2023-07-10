package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.payload.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.payload.ProductResponse;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto);
	
	ProductResponse getAllProducts(int page, int linesPerPage, String orderBy, String direction);
	
	ProductDto getProductById(long id);
	
	ProductDto updateProduct(ProductDto productDto, long id);
	
	void deleteProductById(long id);
}
