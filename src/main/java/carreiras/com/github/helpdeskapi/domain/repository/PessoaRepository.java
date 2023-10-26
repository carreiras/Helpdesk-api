package carreiras.com.github.helpdeskapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import carreiras.com.github.helpdeskapi.domain.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByCpf(String cpf);

    Optional<Pessoa> findByEmail(String email);

}
