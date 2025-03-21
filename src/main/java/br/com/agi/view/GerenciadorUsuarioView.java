package br.com.agi.view;

import br.com.agi.dao.UsuarioDAO;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class GerenciadorUsuarioView {
    public void GerenciadorDeUsuario(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Gerenciador de Usuários\n" +
                "Escolha uma das opções para prosseguir:\n" +
                "1 - Listar Usuários\n" +
                "2 - Selecionar Usuário");

        System.out.print("Digite a opção desejada: ");
        int opcao = sc.nextInt();

        switch(opcao) {
            case 1:
                System.out.println("Listando Usuários");
                //Listar Usuario;
                UsuarioDAO listaUser = new UsuarioDAO();
                listaUser.listarUsuarios();
                break;
            case 2:
                System.out.println("Digite o nome do Usuário");
                String nomeDoUsuario = sc.nextLine();
                //verificação de usuário(se ele existe)
                //if(usuario existe){
                //TelaDeAlteracao();
                System.out.println("Escolha uma opção:\n" +
                        "1 - Alterar Senha:" +
                        "2 - Alterar E-mail" +
                        "3 - Alterar Permissão" +
                        "4 - Remover Nome:" +
                        "5 - Delete Usuário:" +
                        "6 - Retorna ao menu" +
                        "7 - Encerrar");
                opcao = sc.nextInt();
                switch (opcao) {
                    case 1:
                        //chama o método alterar senha
                        UsuarioDAO senha = new UsuarioDAO();
                        senha.updateSenha();
                        break;
                    case 2:
                        //chama o método alterar e-mail
                        UsuarioDAO email = new UsuarioDAO();
                        email.updateEmail();
                        break;
                    case 3:
                        //chama o método alterar Nome
                        UsuarioDAO nome = new UsuarioDAO();
                        nome.UpdateNome();
                        break;
                    case 4:
                        //chama o método alterar permissão
                        UsuarioDAO permissao = new UsuarioDAO();
                        permissao.updatePermissao();
                        break;
                    case 5:
                        //chama o método alterar Delete
                        UsuarioDAO delete = new UsuarioDAO();
                        delete.deletarUsuario();
                        break;
                    case 6:
                        //chama o método retornar ao menu
                        System.out.println("Voltando ao Menu Principal!");
                        return;
                    default:
                        System.out.println("Encerrando");
                }
            default:
                System.out.println("Encerrando");
        }
    }

    public void TelaDeAlteracao(){
        // buscar nome no banco
    }
}
