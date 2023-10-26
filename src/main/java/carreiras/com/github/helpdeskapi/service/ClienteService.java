package carreiras.com.github.helpdeskapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import carreiras.com.github.helpdeskapi.domain.dto.ClienteDTO;
import carreiras.com.github.helpdeskapi.domain.entity.Pessoa;
import carreiras.com.github.helpdeskapi.domain.entity.Cliente;
import carreiras.com.github.helpdeskapi.domain.repository.PessoaRepository;
import carreiras.com.github.helpdeskapi.domain.repository.ClienteRepository;
import carreiras.com.github.helpdeskapi.exception.JdbcSQLIntegrityConstraintViolationException;
import carreiras.com.github.helpdeskapi.exception.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        validaPorCpfEEmail(clienteDTO);
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
        Cliente cliente = findById(id);
        validaPorCpfEEmail(clienteDTO);

        clienteDTO.setId(id);
        cliente = new Cliente(clienteDTO);

        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);

        if (cliente.getChamados().size() > 0)
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado.");

        clienteRepository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());

        if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId())
            throw new JdbcSQLIntegrityConstraintViolationException("CPF já cadastrado no sistema.");

        pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());

        if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId())
            throw new JdbcSQLIntegrityConstraintViolationException("E-mail já cadastrado no sistema.");
    }

}
