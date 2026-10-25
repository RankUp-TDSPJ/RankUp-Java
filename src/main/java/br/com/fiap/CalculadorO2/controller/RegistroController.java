package br.com.fiap.CalculadorO2.controller;

import br.com.fiap.CalculadorO2.dto.AcaoResponse;
import br.com.fiap.CalculadorO2.dto.HistoricoResponse;
import br.com.fiap.CalculadorO2.dto.RegistroCriadoResponse;
import br.com.fiap.CalculadorO2.dto.RegistroRequest;
import br.com.fiap.CalculadorO2.mapper.AcaoMapper;
import br.com.fiap.CalculadorO2.mapper.RegistroMapper;
import br.com.fiap.CalculadorO2.models.RegistroAcao;
import br.com.fiap.CalculadorO2.models.Usuario;
import br.com.fiap.CalculadorO2.service.AcaoService;
import br.com.fiap.CalculadorO2.service.RegistroAcaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistroController {

    private final RegistroAcaoService registroAcaoBO;
    private final AcaoService acaoBO;

    public RegistroController(RegistroAcaoService registroAcaoBO, AcaoService acaoBO) {
        this.registroAcaoBO = registroAcaoBO;
        this.acaoBO = acaoBO;
    }

    @GetMapping("/acoes")
    public List<AcaoResponse> acoes() {
        return acaoBO.listar().stream().map(AcaoMapper::toResponse).toList();
    }

    @PostMapping("/usuarios/{id}/registros")
    public ResponseEntity<RegistroCriadoResponse> registrar(@PathVariable("id") int id,
                                                            @RequestBody RegistroRequest req) {
        Usuario usuario = registroAcaoBO.registrar(id, req.nomeAcao(), req.quantidade(), req.data());

        List<RegistroAcao> registros = usuario.getPontuacao().getRegistros();
        RegistroAcao criado = registros.get(registros.size() - 1);

        return ResponseEntity.status(201).body(RegistroMapper.toCriado(criado, usuario));
    }

    @GetMapping("/usuarios/{id}/registros")
    public List<HistoricoResponse> historico(@PathVariable("id") int id) {
        return registroAcaoBO.historico(id).stream().map(RegistroMapper::toHistorico).toList();
    }
}
