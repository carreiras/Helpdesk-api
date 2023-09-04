package carreiras.com.github.helpdeskapi.service;

import java.util.Optional;

import carreiras.com.github.helpdeskapi.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carreiras.com.github.helpdeskapi.domain.entity.Tecnico;
import carreiras.com.github.helpdeskapi.domain.repository.TecnicoRepository;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

}
