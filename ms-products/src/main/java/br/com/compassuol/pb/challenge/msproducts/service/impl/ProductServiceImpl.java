package br.com.compassuol.pb.challenge.msproducts.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.compassuol.pb.challenge.msproducts.entities.Category;
import br.com.compassuol.pb.challenge.msproducts.entities.Product;
import br.com.compassuol.pb.challenge.msproducts.exceptions.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msproducts.payload.CategoryDto;
import br.com.compassuol.pb.challenge.msproducts.payload.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.payload.ProductResponse;
import br.com.compassuol.pb.challenge.msproducts.repositories.CategoryRepository;
import br.com.compassuol.pb.challenge.msproducts.repositories.ProductRepository;
import br.com.compassuol.pb.challenge.msproducts.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	private ModelMapper mapper;
	
	private CategoryRepository categoryRepository;

	public ProductServiceImpl(ProductRepository productRepository,
							 ModelMapper mapper,
							 CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.mapper = mapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		
		Set<Category> categories = new HashSet<>();
		
		Set<CategoryDto> categoriesDto =  productDto.getCategories();
		categoriesDto.forEach(category -> {
		Category categorySearch = categoryRepository.findById(category.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Category", "id", category.getId()));
		categories.add(categorySearch);
		});
		
		Product product = mapToEntity(productDto);
		product.setCategories(categories);
		Product savedProduct = productRepository.save(product);
		
		ProductDto productResponse = mapToDto(savedProduct);
		return productResponse;
	}
	
	
	@Override
	public ProductResponse getAllProducts(int pageNo, int linesPerPage, String orderBy, String direction) {
		
		Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending()
				: Sort.by(orderBy).descending();
		Pageable pageable = PageRequest.of(pageNo, linesPerPage, sort);
		
		Page<Product> products = productRepository.findAll(pageable);
		
		List<Product> listOfProducts = products.getContent();
		
		List<ProductDto> content = listOfProducts.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
	
		ProductResponse productResponse = new ProductResponse();
		productResponse.setProducts(content);
		productResponse.setPageNo(products.getNumber());
		productResponse.setLinesPerPage(products.getSize());
		
		return productResponse;
	}
	
	@Override
	public ProductDto getProductById(long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
		
		return mapToDto(product);
	}
	
	
	@Override
	public ProductDto updateProduct(ProductDto productDto, long id) {

		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
		
		Product newProduct = mapToEntity(productDto);
		
		product.setCategories(newProduct.getCategories());
		product.setDate(newProduct.getDate());
		product.setDescription(newProduct.getDescription());
		product.setName(newProduct.getName());
		product.setImgUrl(newProduct.getImgUrl());
		product.setPrice(newProduct.getPrice());
		
		Product updatedProduct = productRepository.save(product); 
		
		return mapToDto(updatedProduct);
	}
	
	@Override
	public void deleteProductById(long id) {
		
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
		productRepository.delete(product);
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
