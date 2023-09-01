package carreiras.com.github.helpdeskapi.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carreiras.com.github.helpdeskapi.domain.entity.Chamado;
import carreiras.com.github.helpdeskapi.domain.entity.Cliente;
import carreiras.com.github.helpdeskapi.domain.entity.Tecnico;
import carreiras.com.github.helpdeskapi.domain.enums.Perfil;
import carreiras.com.github.helpdeskapi.domain.enums.Prioridade;
import carreiras.com.github.helpdeskapi.domain.enums.Status;
import carreiras.com.github.helpdeskapi.domain.repository.ChamadoRepository;
import carreiras.com.github.helpdeskapi.domain.repository.ClienteRepository;
import carreiras.com.github.helpdeskapi.domain.repository.TecnicoRepository;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tecnico1 = new Tecnico(
                null,
                "Plaze",
                "62938379080",
                "plaze@email.com",
                "lyie3m4mgl");
        tecnico1.addPerfis(Perfil.ADMIN);

        Cliente cliente1 = new Cliente(
                null,
                "Walay",
                "54331062090",
                "walay@email.com",
                "i9EyU1RNnA");

        Chamado chamado1 = new Chamado(
                null,
                Prioridade.MEDIA,
                Status.ANDAMENTO,
                "Chamado #1",
                "Primeiro Chamado",
                tecnico1,
                cliente1);

        tecnicoRepository.saveAll(Arrays.asList(tecnico1));
        clienteRepository.saveAll(Arrays.asList(cliente1));
        chamadoRepository.saveAll(Arrays.asList(chamado1));
    }
    
}
