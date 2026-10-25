package br.com.fiap.CalculadorO2.controller;

import br.com.fiap.CalculadorO2.mapper.UsuarioMapper;
import br.com.fiap.CalculadorO2.models.Usuario;
import br.com.fiap.CalculadorO2.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioBO) {
        this.usuarioService = usuarioBO;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody CadastroRequest req) {
        Usuario usuario = usuarioService.cadastrar(req.nome(), req.email(), req.idade(), req.codigoAdmin());
        return ResponseEntity.status(201).body(UsuarioMapper.toResponse(usuario));
    }

    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody LoginRequest req) {
        return UsuarioMapper.toResponse(usuarioService.login(req.email()));
    }

    @GetMapping("/usuarios/{id}")
    public PerfilResponse perfil(@PathVariable("id") int id) {
        return UsuarioMapper.toPerfil(usuarioService.buscarComHistorico(id));
    }

    @PutMapping("/usuarios/{id}")
    public UsuarioResponse atualizar(@PathVariable("id") int id, @RequestBody AtualizarUsuarioRequest req) {
        return UsuarioMapper.toResponse(usuarioService.atualizar(id, req.nome(), req.email(), req.idade()));
    }

    // required = false: sem o cabeçalho, o valor chega null e a BO responde 403
    @GetMapping("/usuarios")
    public List<UsuarioResumoResponse> listar(
            @RequestHeader(value = "X-Usuario-Id", required = false) Integer idSolicitante) {
        return usuarioService.listarTodos(idSolicitante).stream().map(UsuarioMapper::toResumo).toList();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deletar(
            @RequestHeader(value = "X-Usuario-Id", required = false) Integer idSolicitante,
            @PathVariable("id") int id) {
        usuarioService.deletar(idSolicitante, id);
        return ResponseEntity.noContent().build();   // 204
    }
}
