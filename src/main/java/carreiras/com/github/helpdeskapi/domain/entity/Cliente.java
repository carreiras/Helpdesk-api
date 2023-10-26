package carreiras.com.github.helpdeskapi.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import carreiras.com.github.helpdeskapi.domain.dto.ClienteDTO;
import carreiras.com.github.helpdeskapi.domain.enums.Perfil;

@Entity
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(
            Integer id,
            String nome,
            String cpf,
            String email,
            String senha) {
        super(
                id,
                nome,
                cpf,
                email,
                senha);
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO clienteDTO) {
        Set<Integer> perfis = clienteDTO.getPerfis().stream()
                .map(m -> m.getCodigo())
                .collect(Collectors.toSet());

        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
        this.senha = clienteDTO.getSenha();
        this.perfis = perfis;
        this.dataCriacao = clienteDTO.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

}
