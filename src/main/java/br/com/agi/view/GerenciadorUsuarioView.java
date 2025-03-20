package br.com.agi.view;

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
                        "4 - Retorna ao menu" +
                        "5 - Encerrar");
                opcao = sc.nextInt();
                switch (opcao){
                    case 1:
                        //chama o método alterar senha
                        break;
                    case 2:
                        //chama o método alterar e-mail
                        break;
                    case 3:
                        //chama o método alterar permissão
                        break;
                    case 4:
                        //chama o método retornar ao menu
                        break;
                    default:
                        System.out.println("Encerrando");
                }
//                }else {
//                print "Usuario não existe"}
//
                //Alterar Nome
                break;
            case 3:
                System.out.println("Alterar senha");
                //Alterar Senha
                break;
            case 4:
                System.out.println("Alterar e-Mail");
                //Alterar e-mail
                break;
            case 5:
                //Alterar Permissões
                break;
            default:
                System.out.println("Encerrado");
        };
    }

    public void TelaDeAlteracao(){
        // buscar nome no banco
    }

    // listar todos os usuarios, opções de alteração de usuario, o que quer alterar(senha, nome, permissão, email
}
