package br.com.fiap.CalculadorO2.service;

import br.com.fiap.CalculadorO2.dao.RegistroAcaoDAO;
import br.com.fiap.CalculadorO2.dao.UsuarioDAO;
import br.com.fiap.CalculadorO2.exception.ApiException;
import br.com.fiap.CalculadorO2.models.RegistroAcao;
import br.com.fiap.CalculadorO2.models.Usuario;
import br.com.fiap.CalculadorO2.models.UsuarioAdmin;
import br.com.fiap.CalculadorO2.models.UsuarioComum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private static final String CODIGO_ADMIN = "senha";

    private final UsuarioDAO usuarioDAO;
    private final RegistroAcaoDAO registroAcaoDAO;
    private final AcaoService acaoBO;

    public UsuarioService(UsuarioDAO usuarioDAO, RegistroAcaoDAO registroAcaoDAO, AcaoService acaoBO) {
        this.usuarioDAO = usuarioDAO;
        this.registroAcaoDAO = registroAcaoDAO;
        this.acaoBO = acaoBO;
    }

    public Usuario cadastrar(String nome, String email, int idade, String codigoAdmin) {
        validarCampos(nome, email, idade);

        if (usuarioDAO.buscarPorEmailUser(email) != null) {
            throw new ApiException(409, "E-mail já cadastrado");
        }

        Usuario usuario;
        if (CODIGO_ADMIN.equals(codigoAdmin)) {
            usuario = new UsuarioAdmin(nome, email, idade);
        } else {
            usuario = new UsuarioComum(nome, email, idade);   // código errado ou ausente = comum
        }

        usuario.setId(usuarioDAO.proximoIdUser());
        usuarioDAO.cadastrarUser(usuario);
        return usuario;
    }

    public Usuario login(String email) {
        if (email == null || email.isBlank()) {
            throw new ApiException(400, "E-mail é obrigatório");
        }
        Usuario usuario = usuarioDAO.buscarPorEmailUser(email);
        if (usuario == null) {
            throw new ApiException(404, "E-mail não encontrado");
        }
        return usuario;
    }

    public Usuario buscarComHistorico(int id) {
        Usuario usuario = usuarioDAO.buscarPorIdUser(id);
        if (usuario == null) {
            throw new ApiException(404, "Usuário não encontrado");
        }
        List<RegistroAcao> historico = registroAcaoDAO.listarPorUsuario(id, acaoBO.listar());
        usuario.getPontuacao().carregarRegistros(historico);
        return usuario;
    }

    public Usuario atualizar(int id, String nome, String email, int idade) {
        Usuario usuario = usuarioDAO.buscarPorIdUser(id);
        if (usuario == null) {
            throw new ApiException(404, "Usuário não encontrado");
        }
        validarCampos(nome, email, idade);

        Usuario outro = usuarioDAO.buscarPorEmailUser(email);
        if (outro != null && outro.getId() != id) {
            throw new ApiException(409, "E-mail já usado por outra pessoa");
        }

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setIdade(idade);
        usuarioDAO.updateUser(usuario);
        return usuario;
    }

    public List<Usuario> listarTodos(Integer idSolicitante) {
        exigirAdmin(idSolicitante);
        return usuarioDAO.listarTodosUser();
    }

    public void deletar(Integer idSolicitante, int id) {
        exigirAdmin(idSolicitante);   // 403 vem antes do 404, pra não revelar quem existe

        if (usuarioDAO.buscarPorIdUser(id) == null) {
            throw new ApiException(404, "Usuário não encontrado");
        }
        registroAcaoDAO.deletarRegistroUser(id);
        usuarioDAO.deleteUser(id);
    }

    private void exigirAdmin(Integer idSolicitante) {
        if (idSolicitante == null) {
            throw new ApiException(403, "Cabeçalho X-Usuario-Id é obrigatório");
        }
        Usuario solicitante = usuarioDAO.buscarPorIdUser(idSolicitante);
        if (solicitante == null || !"ADMIN".equals(solicitante.getTipo())) {
            throw new ApiException(403, "Apenas administradores podem executar esta ação");
        }
    }

    private void validarCampos(String nome, String email, int idade) {
        if (nome == null || nome.isBlank()) {
            throw new ApiException(400, "Nome é obrigatório");
        }
        if (email == null || !email.contains("@")) {
            throw new ApiException(400, "E-mail inválido");
        }
        if (idade <= 0) {
            throw new ApiException(400, "Idade deve ser maior que 0");
        }
    }


}
