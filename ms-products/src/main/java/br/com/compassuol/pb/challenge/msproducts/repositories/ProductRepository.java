package br.com.compassuol.pb.challenge.msproducts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compassuol.pb.challenge.msproducts.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
