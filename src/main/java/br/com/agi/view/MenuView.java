package br.com.agi.view;
import java.util.Scanner;

public class MenuView {
    private Scanner sc;

    public MenuView(){
        this.sc = new Scanner (System.in);
    }

    public void ExibirMenuInicial(){
        char opcao;

        while(true){
            System.out.print("\n==============================\n");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Acessar conta");
            System.out.println("3 - Menu de faturamento");
            System.out.println("4 - Ajustar taxas");
            System.out.println("5 - Listar cobranças por cliente");
            System.out.println("6 - Sair");

            System.out.print(":");
            opcao = sc.next().charAt(0);
            sc.nextLine();

            switch (opcao){
                case '1':
                    System.out.println("🚧 Criar Conta - Em desenvolvimento...");
                    break;
                case '2':
                    ExibirTelaLogin();
                    break;
                case '3':
                    System.out.println("🚧 Menu de Faturamento - Em desenvolvimento...");
                    break;
                case '4':
                    TaxaMenuView taxaMenuView = new TaxaMenuView();  //alterado por Tulio
                    taxaMenuView.telaInicial();
                    break;
                case '5':
                    System.out.println("🚧 Lista de Cobranças - Em desenvolvimento... ");
                    break;
                case '6':
                    System.out.println("Saindo do sistema... Até logo!");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }

        }
    }

    private void ExibirTelaLogin(){
        System.out.println("\n===== LOGIN =====");
        System.out.print("Digite seu CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        System.out.println("🚧 Login em desenvolvimento...");
    }
}
