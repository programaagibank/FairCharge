package br.com.agi.view.inicios;
import br.com.agi.controller.UsuarioController;
import br.com.agi.dao.UsuarioDAO;
import java.util.Scanner;

public class TelaCadastro {
    private final UsuarioDAO usuarioDAO;
    private TelaInicial telaInicial = new TelaInicial();
    private UsuarioController usuarioController;
    private Scanner sc = new Scanner(System.in);

    public TelaCadastro(){
        this.telaInicial = new TelaInicial();
        this.usuarioDAO = new UsuarioDAO();
        this.usuarioController = new UsuarioController();
    }

    public void iniciarCadastro() {
        System.out.println("\n===== CADASTRO DE USUÁRIO =====");

        String nome;
        String email;
        String senha;
        String permissao;
        do {
            System.out.print("Digite seu nome: ");
            nome = sc.nextLine().trim();

            System.out.print("Digite seu email: ");
            email = sc.nextLine().trim();

            System.out.print("Digite sua senha: ");
            senha = sc.nextLine().trim();

            String textoPermissao = """
                    Digite a permissão do usuário:
                    1 - ADMINISTRADOR
                    2 - FINANCEIRO
                    """;
            System.out.println(textoPermissao);
            permissao = sc.nextLine();
            if (usuarioController.cadastroUsuario(nome, email, senha, permissao)) {
                System.out.println("Usuário cadastrado com sucesso!");
                break;
            } else {
                System.out.println("Erro ao cadastrar usuário! Tente novamente.");
            }
        } while (true);
    }
}
