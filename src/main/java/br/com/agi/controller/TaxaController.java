package br.com.agi.controller;

import br.com.agi.dao.TaxaDAO;
import java.util.Scanner;

public class TaxaController {
    private TaxaDAO taxaDAO;
    private Scanner sc;

    public TaxaController() {
        this.taxaDAO = new TaxaDAO();
        this.sc = new Scanner(System.in);
    }

    // Método para obter a taxa de juros diários
    public double getTaxaJuros() {
        return taxaDAO.buscarTaxa("Juros");
    }

    // Método para obter a taxa de multa por atraso
    public double getTaxaMulta() {
        return taxaDAO.buscarTaxa("Multa");
    }

    // Método para atualizar a taxa de juros diários
    public void ajustarTaxaJuros() {
        double taxaAtual = getTaxaJuros();  // Chama o método para obter a taxa de juros atual
        System.out.println("Taxa de Juros Diários atual: " + taxaAtual + "%");

        System.out.print("Digite a nova taxa de juros diários: ");
        try {
            double novaTaxa = sc.nextDouble();
            boolean sucesso = taxaDAO.atualizarTaxa("Juros", novaTaxa);  // Atualiza a taxa de juros no banco
            if (sucesso) {
                System.out.println("Taxa de juros diários atualizada com sucesso para " + novaTaxa + "%.");
            } else {
                System.out.println("Erro ao atualizar a taxa de juros diários.");
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida! Por favor, insira um número válido.");
            sc.nextLine(); // Limpa o buffer do Scanner
        }
    }

    // Método para atualizar a taxa de multa por atraso
    public void ajustarTaxaMulta() {
        double taxaAtual = getTaxaMulta();  // Chama o método para obter a taxa de multa atual
        System.out.println("Taxa de Multa por Atraso atual: " + taxaAtual + "%");

        System.out.print("Digite a nova taxa de multa por atraso: ");
        try {
            double novaTaxa = sc.nextDouble();
            boolean sucesso = taxaDAO.atualizarTaxa("Multa", novaTaxa);  // Atualiza a taxa de multa no banco
            if (sucesso) {
                System.out.println("Taxa de multa por atraso atualizada com sucesso para " + novaTaxa + "%.");
            } else {
                System.out.println("Erro ao atualizar a taxa de multa por atraso.");
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida! Por favor, insira um número válido.");
            sc.nextLine(); // Limpa o buffer do Scanner
        }
    }

}
