package br.com.agi.controller;

import br.com.agi.model.Usuario;
import br.com.agi.utils.Alerta;
import br.com.agi.utils.DialogHelper;
import br.com.agi.utils.Navegador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class GerenciadorUsuariosController {
    private UsuarioController controller = new UsuarioController();

    @FXML
    private TableView<Usuario> userTable;

    @FXML
    private TableColumn<Usuario, String> colNome;

    @FXML
    private TableColumn<Usuario, String> colEmail;

    @FXML
    private TableColumn<Usuario, Integer> colPermissao;

    @FXML
    void handleExcluirUsuario() {
        Usuario usuarioSelecionado = userTable.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado != null) {
            boolean confirmado = Alerta.mostrarConfirmacao(
                    "Confirmar Exclusão",
                    "Você realmente deseja excluir este usuário?",
                    "Usuário: " + usuarioSelecionado.getNome()
            );

            if (confirmado) {
                boolean sucesso = controller.deletarUsuario(usuarioSelecionado.getEmail());
                if (sucesso) {
                    Alerta.mostrarInformacao("Sucesso", "Usuário Excluído", "O usuário foi excluído com sucesso.");
                    preencherTabela();
                } else {
                    Alerta.mostrarErro("Erro", "Erro ao Excluir Usuário", "Não foi possível excluir o usuário do banco de dados.");
                }
            }
        } else {
            Alerta.mostrarAviso("Nenhum Usuário Selecionado", null, "Por favor, selecione um usuário na tabela para excluir.");
        }
    }

    @FXML
    void handleEditarUsuario() {
        Usuario usuarioSelecionado = userTable.getSelectionModel().getSelectedItem();
        if (usuarioSelecionado != null) {
            Optional<Usuario> resultado = DialogHelper.solicitarEdicaoUsuario(usuarioSelecionado);
            resultado.ifPresent(usuarioEditado -> {
                boolean sucesso = controller.atualizarUsuarioFX(usuarioEditado);

                if (sucesso) {
                    Alerta.mostrarInformacao("Sucesso", "Usuário Editado", "As informações do usuário foram atualizadas com sucesso!");
                    preencherTabela();
                } else {
                    Alerta.mostrarErro("Erro", "Erro ao Editar Usuário", "Não foi possível atualizar as informações do usuário.");
                }
            });
        } else {
            Alerta.mostrarAviso("Nenhum Usuário Selecionado", null, "Por favor, selecione um usuário na tabela para editar.");
        }
    }

    @FXML
    void handleAdicionarUsuario() {
        Optional<Usuario> resultado = DialogHelper.solicitarInformacoesUsuario();

        resultado.ifPresent(novoUsuario -> {
            boolean sucesso = controller.cadastroUsuario(novoUsuario.getNome(), novoUsuario.getEmail(), novoUsuario.getSenha(), String.valueOf(novoUsuario.getPermissao()));

            if (sucesso) {
                Alerta.mostrarInformacao("Sucesso", "Usuário Adicionado", "O usuário foi cadastrado com sucesso!");
                preencherTabela();
            } else {
                Alerta.mostrarErro("Erro", "Erro ao Adicionar Usuário", "Não foi possível cadastrar o usuário.");
            }
        });
    }

    @FXML
    void handleVoltarButton() {
        Navegador.getHome();
    }

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colPermissao.setCellValueFactory(new PropertyValueFactory<>("permissao"));
        colPermissao.setCellFactory(column -> new TableCell<Usuario, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(converterPermissao(item));
                }
            }
        });

        preencherTabela();
    }

    private void preencherTabela() {
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList(controller.listarTodosUsuariosFX());
        userTable.setItems(usuarios);
    }

    private String converterPermissao(int permissao) {
        switch (permissao) {
            case 1:
                return "Administrador";
            case 2:
                return "Financeiro";
            default:
                return "Desconhecido";
        }
    }
}
