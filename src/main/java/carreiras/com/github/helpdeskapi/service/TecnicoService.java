package carreiras.com.github.helpdeskapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import carreiras.com.github.helpdeskapi.domain.dto.TecnicoDTO;
import carreiras.com.github.helpdeskapi.domain.entity.Pessoa;
import carreiras.com.github.helpdeskapi.domain.entity.Tecnico;
import carreiras.com.github.helpdeskapi.domain.repository.PessoaRepository;
import carreiras.com.github.helpdeskapi.domain.repository.TecnicoRepository;
import carreiras.com.github.helpdeskapi.exception.JdbcSQLIntegrityConstraintViolationException;
import carreiras.com.github.helpdeskapi.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validaPorCpfEEmail(tecnicoDTO);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = findById(id);
        validaPorCpfEEmail(tecnicoDTO);

        tecnicoDTO.setId(id);
        tecnico = new Tecnico(tecnicoDTO);

        return tecnicoRepository.save(tecnico);
    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);

        if (tecnico.getChamados().size() > 0)
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado.");

        tecnicoRepository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());

        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId())
            throw new JdbcSQLIntegrityConstraintViolationException("CPF já cadastrado no sistema.");

        pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());

        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId())
            throw new JdbcSQLIntegrityConstraintViolationException("E-mail já cadastrado no sistema.");
    }

}
