package br.com.compassuol.pb.challenge.msusers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compassuol.pb.challenge.msusers.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
