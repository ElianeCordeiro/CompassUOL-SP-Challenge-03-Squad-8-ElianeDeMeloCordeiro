package br.com.compassuol.pb.challenge.msproducts.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.msproducts.entities.Product;
import br.com.compassuol.pb.challenge.msproducts.payload.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.repositories.ProductRepository;
import br.com.compassuol.pb.challenge.msproducts.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	private ModelMapper mapper;
	

	public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
		this.productRepository = productRepository;
		this.mapper = mapper;
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		
		Product product = mapToEntity(productDto);
		Product savedProduct = productRepository.save(product);
		
		ProductDto productResponse = mapToDto(savedProduct);
		return productResponse;
	}

	private ProductDto mapToDto(Product product) {
		ProductDto productDto = mapper.map(product, ProductDto.class);
		return productDto;
	}
	
	private Product mapToEntity(ProductDto productDto) {
		Product product = mapper.map(productDto, Product.class);
		return product;
	}
}
