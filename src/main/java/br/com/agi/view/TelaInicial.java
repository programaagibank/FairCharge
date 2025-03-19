package br.com.agi.view;
import java.util.Scanner;

public class TelaInicial {
    Scanner sc = new Scanner(System.in);
    public void TelaMenu() {
        char opcao;

        while (true) {

            System.out.print("==============================\n");
            System.out.println("Escolha a opção desejada:");
            System.out.println("1 - Acessar conta");
            System.out.println("2 - Criar conta");
            System.out.println("3 - Sair");

            System.out.print(":");
            opcao = sc.next().charAt(0);
            sc.nextLine();

            switch (opcao) {
                case '1':
                    //ExibirTelaLogin();
                    break;
                case '2':
                    //TelaCadastro();
                    break;
                case '3':
                    System.out.println("Saindo do sistema... Até logo!");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }

        }
    }
}
