package br.com.compassuol.pb.challenge.msproducts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.pb.challenge.msproducts.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
