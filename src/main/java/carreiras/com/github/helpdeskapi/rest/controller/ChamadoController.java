package carreiras.com.github.helpdeskapi.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carreiras.com.github.helpdeskapi.domain.dto.ChamadoDTO;
import carreiras.com.github.helpdeskapi.domain.entity.Chamado;
import carreiras.com.github.helpdeskapi.service.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> chamados = chamadoService.findAll();
        List<ChamadoDTO> chamadosDTO = chamados.stream()
                .map(m -> new ChamadoDTO(m))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(chamadosDTO);
    }

}
