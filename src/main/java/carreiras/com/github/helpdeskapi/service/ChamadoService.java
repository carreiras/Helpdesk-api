package carreiras.com.github.helpdeskapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carreiras.com.github.helpdeskapi.domain.entity.Chamado;
import carreiras.com.github.helpdeskapi.domain.repository.ChamadoRepository;
import carreiras.com.github.helpdeskapi.exception.ObjectNotFoundException;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }
}
