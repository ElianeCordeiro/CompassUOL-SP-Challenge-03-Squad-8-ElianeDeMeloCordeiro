package br.com.compassuol.pb.challenge.msusers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compassuol.pb.challenge.msusers.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
