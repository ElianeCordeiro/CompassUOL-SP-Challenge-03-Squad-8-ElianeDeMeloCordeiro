package br.com.compassuol.pb.challenge.msproducts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassuol.pb.challenge.msproducts.payload.ProductDto;
import br.com.compassuol.pb.challenge.msproducts.payload.ProductResponse;
import br.com.compassuol.pb.challenge.msproducts.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto){
		return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ProductResponse getAllProduct(
			@RequestParam(value = "page", defaultValue = "0", required = false)int page,
			@RequestParam(value = "linesPerPage", defaultValue = "5", required = false) int linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name", required =false) String orderBy,
			@RequestParam(value = "direction", defaultValue = "asc", required = false) String direction
	){
		return productService.getAllProducts(page, linesPerPage, orderBy, direction);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") long id){
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable(name="id") long id){
		
		ProductDto productResponse = productService.updateProduct(productDto, id);
		
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable(name="id") long id){
		productService.deleteProductById(id);
		
		return new ResponseEntity<>("Product entity deleted successfully.", HttpStatus.OK);
	}
}
