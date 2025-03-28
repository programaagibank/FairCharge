package br.com.agi.controller;
import br.com.agi.dao.FaturaDAO;
import java.util.Scanner;

public class FaturaController {

    private final FaturaDAO faturaDAO = new FaturaDAO();

    public void atualizarStatusFatura() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID da fatura para atualizacao: ");
        int faturaId = scanner.nextInt();

        faturaDAO.atualizarStatusFatura(faturaId);
    }

    public static void main(String[] args) {
        FaturaController controller = new FaturaController();
        controller.atualizarStatusFatura();
    }
}
