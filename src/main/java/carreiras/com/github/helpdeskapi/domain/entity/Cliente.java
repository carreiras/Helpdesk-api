package carreiras.com.github.helpdeskapi.domain.entity;

import java.util.ArrayList;
import java.util.List;

import carreiras.com.github.helpdeskapi.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        addPerfil(Perfil.CLIENTE);
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
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

}