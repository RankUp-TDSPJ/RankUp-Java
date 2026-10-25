package br.com.fiap.CalculadorO2.service;

import br.com.fiap.CalculadorO2.dao.RegistroAcaoDAO;
import br.com.fiap.CalculadorO2.dao.UsuarioDAO;
import br.com.fiap.CalculadorO2.exception.ApiException;
import br.com.fiap.CalculadorO2.models.AcaoSustentavel;
import br.com.fiap.CalculadorO2.models.RegistroAcao;
import br.com.fiap.CalculadorO2.models.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class RegistroAcaoService {

    private final UsuarioService usuarioBO;
    private final AcaoService acaoBO;
    private final UsuarioDAO usuarioDAO;
    private final RegistroAcaoDAO registroAcaoDAO;

    public RegistroAcaoService(UsuarioService usuarioBO, AcaoService acaoBO,
                               UsuarioDAO usuarioDAO, RegistroAcaoDAO registroAcaoDAO) {
        this.usuarioBO = usuarioBO;
        this.acaoBO = acaoBO;
        this.usuarioDAO = usuarioDAO;
        this.registroAcaoDAO = registroAcaoDAO;
    }

    public Usuario registrar(int idUsuario, String nomeAcao, double quantidade, String data) {
        if (quantidade <= 0) {
            throw new ApiException(400, "Quantidade deve ser maior que 0");
        }
        if (data == null) {
            throw new ApiException(400, "Data é obrigatória (yyyy-MM-dd)");
        }
        try {
            LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            throw new ApiException(400, "Data inválida, use o formato yyyy-MM-dd");
        }

        Usuario usuario = usuarioBO.buscarComHistorico(idUsuario);
        AcaoSustentavel acao = acaoBO.buscarPorNome(nomeAcao);
        if (acao == null) {
            throw new ApiException(404, "Ação não encontrada");
        }

        RegistroAcao registro = new RegistroAcao(acao, quantidade, data);
        registro.setId_acao(registroAcaoDAO.proximoIdAcao());

        usuario.getPontuacao().adicionarRegistro(registro);
        registroAcaoDAO.cadastrarAcao(idUsuario, registro);
        usuarioDAO.updateUser(usuario);

        return usuario;
    }

    public List<RegistroAcao> historico(int idUsuario) {
        return usuarioBO.buscarComHistorico(idUsuario).getPontuacao().getRegistros();
    }
}
