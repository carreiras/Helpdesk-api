package carreiras.com.github.helpdeskapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import carreiras.com.github.helpdeskapi.domain.entity.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
