package br.com.compassuol.pb.challenge.msproducts.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="imgUrl", nullable=false)
	private String imgUrl;
	
	@Column(name="price", nullable=false)
	private double price;
}
