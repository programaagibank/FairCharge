package br.com.agi.view.inicios;
import br.com.agi.dao.UsuarioDAO;
import java.util.Scanner;

public class TelaCadastro {
    private final UsuarioDAO usuarioDAO;
    private TelaInicial telaInicial = new TelaInicial();
    private Scanner sc = new Scanner(System.in);

    public TelaCadastro(){
        this.telaInicial = new TelaInicial();
        this.usuarioDAO = new UsuarioDAO();
    }

    public void iniciarCadastro() {
        System.out.println("\n===== CADASTRO DE USUÁRIO =====");

        String nome;
        String email;
        String senha;

        do {
            System.out.print("Digite seu nome: ");
            nome = sc.nextLine().trim();

            System.out.print("Digite seu email: ");
            email = sc.nextLine().trim();

            System.out.print("Digite sua senha: ");
            senha = sc.nextLine().trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                System.out.println("Um dos campos necessários não foi preenchido corretamente.");
            } else if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Email inválido! Digite um email válido.");
            }
        }while (nome.isEmpty() || email.isEmpty() || senha.isEmpty());

        if (usuarioDAO.cadastrarUsuario(nome, email, senha)) {
            System.out.println(" Usuário cadastrado com sucesso!");
            telaInicial.TelaMenu();
        } else {
            System.out.println("Erro ao cadastrar usuário! Tente novamente.");
        }
    }
}
