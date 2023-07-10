package br.com.compassuol.pb.challenge.msproducts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.pb.challenge.msproducts.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
