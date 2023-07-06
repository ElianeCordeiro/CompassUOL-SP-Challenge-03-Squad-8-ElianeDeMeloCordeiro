package br.com.compassuol.pb.challenge.msproducts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compassuol.pb.challenge.msproducts.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
