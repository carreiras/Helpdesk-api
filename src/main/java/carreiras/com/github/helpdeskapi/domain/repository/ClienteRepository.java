package carreiras.com.github.helpdeskapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import carreiras.com.github.helpdeskapi.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
