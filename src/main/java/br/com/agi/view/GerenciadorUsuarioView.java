package br.com.agi.view;
import br.com.agi.dao.UsuarioDAO;
import br.com.agi.controller.UsuarioController;
import java.util.Scanner;

public class GerenciadorUsuarioView {

    public void GerenciadorDeUsuario(){
        Scanner sc = new Scanner(System.in);
        UsuarioController usuarioController = new UsuarioController();

        while(true) {

            System.out.println("\n======GERENCIADOR DE USUARIO======\n" +
                    "1 - Listar Usuarios cadastrados\n" +
                    "2 - Selecionar Usuario\n" +
                    "3 - Voltar");


            System.out.print("Opcao: ");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Listando Usuarios...");
                    usuarioController.listarTodosUsuarios();
                    System.out.println("\nDigite Enter para sair!");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 2:
                    System.out.println("\nDigite o email do Usuario:");
                    sc.nextLine();
                    String emailDoUsuario = sc.nextLine();

                    if (usuarioController.listarUsuario(emailDoUsuario)) {
                        String opcaoGerenciador = """
                                
                                Escolha uma opcao:
                                1 - Alterar Nome
                                2 - Alterar E-mail
                                3 - Alterar Permissao
                                4 - Alterar Senha
                                5 - Deletar Usuario
                                6 - Retornar ao menu
                                
                                """;
                        System.out.print(opcaoGerenciador);
                        System.out.print("Opcao: ");
                        opcao = sc.nextInt();

                        switch (opcao) {
                            case 1:
                                System.out.print("Digite o novo nome: ");
                                sc.nextLine();
                                String novoNome = sc.nextLine();

                                if (usuarioController.atualizarNome(novoNome, emailDoUsuario)) {
                                    System.out.println("Nome atualizado com sucesso!");
                                } else {
                                    System.out.println("Erro ao atualizar nome.");
                                }
                                break;

                            case 2:
                                System.out.print("Digite o novo email:");
                                sc.nextLine();
                                String novoEmail = sc.nextLine();

                                if (usuarioController.atualizarEmail(novoEmail, emailDoUsuario)) {
                                    System.out.println("Email atualizado com sucesso!");
                                } else {
                                    System.out.println("Erro ao atualizar email.");
                                }
                                break;

                            case 3:
                                System.out.print("Digite a nova permissao (1 - Administrador, 2 - Cliente): ");
                                int novaPermissao = sc.nextInt();

                                if (usuarioController.atualizarPermissao(emailDoUsuario, novaPermissao)) {
                                    System.out.println("Permissao atualizada com sucesso!");
                                } else {
                                    System.out.println("Erro ao atualizar permissão.");
                                }
                                break;

                            case 4:
                                System.out.print("Digite a nova senha: ");
                                sc.nextLine();
                                String novaSenha = sc.nextLine();

                                if (usuarioController.atualizarSenha(novaSenha, emailDoUsuario)) {
                                    System.out.println("Senha atualizada com sucesso!");
                                } else {
                                    System.out.println("Erro ao atualizar senha.");
                                }
                                break;

                            case 5:
                                System.out.println("Tem certeza que deseja deletar o usuario? (S/N)");
                                sc.nextLine();
                                String confirmacao = sc.nextLine();

                                if (confirmacao.equalsIgnoreCase("S")) {
                                    if (usuarioController.deletarUsuario(emailDoUsuario)) {
                                        System.out.println("Usuario deletado com sucesso!");
                                    } else {
                                        System.out.println("Erro ao deletar usuario.");
                                    }
                                }
                                break;

                            case 6:
                                System.out.println("Voltando ao Menu Principal!");
                                return;

                            default:
                                System.out.println("Opcao invalida!");
                        }
                    }
                    break;
                case 3:
                    System.out.println("voltando..");
                    return;
                default:
                    System.out.print("Opcao invalida!");
            }
        }
    }

}
