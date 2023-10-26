package carreiras.com.github.helpdeskapi.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import carreiras.com.github.helpdeskapi.domain.dto.TecnicoDTO;
import carreiras.com.github.helpdeskapi.domain.enums.Perfil;

@Entity
public class Tecnico extends Pessoa {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        addPerfis(Perfil.TECNICO);
    }

    public Tecnico(
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
        addPerfis(Perfil.TECNICO);
    }

    public Tecnico(TecnicoDTO tecnicoDTO) {
        Set<Integer> perfis = tecnicoDTO.getPerfis().stream()
                .map(m -> m.getCodigo())
                .collect(Collectors.toSet());

        this.id = tecnicoDTO.getId();
        this.nome = tecnicoDTO.getNome();
        this.cpf = tecnicoDTO.getCpf();
        this.email = tecnicoDTO.getEmail();
        this.senha = tecnicoDTO.getSenha();
        this.perfis = perfis;
        this.dataCriacao = tecnicoDTO.getDataCriacao();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

}
