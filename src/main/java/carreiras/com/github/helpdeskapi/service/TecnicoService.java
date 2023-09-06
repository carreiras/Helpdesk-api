package carreiras.com.github.helpdeskapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carreiras.com.github.helpdeskapi.domain.dto.TecnicoDTO;
import carreiras.com.github.helpdeskapi.domain.entity.Tecnico;
import carreiras.com.github.helpdeskapi.domain.repository.TecnicoRepository;
import carreiras.com.github.helpdeskapi.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }

}
