package carreiras.com.github.helpdeskapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import carreiras.com.github.helpdeskapi.domain.entity.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
