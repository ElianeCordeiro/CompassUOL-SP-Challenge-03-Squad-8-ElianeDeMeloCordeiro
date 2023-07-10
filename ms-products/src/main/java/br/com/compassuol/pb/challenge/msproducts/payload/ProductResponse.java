package br.com.compassuol.pb.challenge.msproducts.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	
	private List<ProductDto> products;
	private int pageNo;
	private int linesPerPage;
	
}
