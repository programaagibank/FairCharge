package br.com.agi.utils;
import br.com.agi.model.CobrancasFaturamento;
import br.com.agi.model.FaturamentoCliente;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import br.com.agi.model.Faturamento;
import br.com.agi.model.CategoriasFaturamento;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportadorPDF {

    public static void exportarBanco(Faturamento faturamento, String caminho) throws IOException {
        PdfWriter writer = new PdfWriter(new FileOutputStream(caminho));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Relatório de Faturamento do Banco - " + faturamento.getMes() + "/" + faturamento.getAno()));
        document.add(new Paragraph("Total de Cobranças: " + faturamento.getTotalCobrancas()));
        document.add(new Paragraph("Total Recebido: R$ " + faturamento.getTotalRecebido()));
        document.add(new Paragraph("Total Pendente: R$ " + faturamento.getTotalPendente()));
        document.add(new Paragraph("Total Inadimplente: R$ " + faturamento.getTotalInadimplente()));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Detalhamento por Categoria:"));
        for (CategoriasFaturamento cat : faturamento.getDetalhamentos()) {
            document.add(new Paragraph(cat.getCategoria() +
                    " | Recebido: R$ " + cat.getRecebidos() +
                    " | Pendente: R$ " + cat.getPendentes() +
                    " | Inadimplente: R$ " + cat.getInadimplentes()));
        }

        document.close();
    }

    public static void exportarCliente(FaturamentoCliente cliente, String caminho) throws IOException {
        PdfWriter writer = new PdfWriter(new FileOutputStream(caminho));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Relatório de Faturamento do Cliente - " + cliente.getMes() + "/" + cliente.getAno()));
        document.add(new Paragraph("Cliente: " + cliente.getCliente()));
        document.add(new Paragraph("Total de Cobranças: " + cliente.getTotalCobrancas()));
        document.add(new Paragraph("Total Recebido: R$ " + cliente.getTotalRecebido()));
        document.add(new Paragraph("Total Pendente: R$ " + cliente.getTotalPendente()));
        document.add(new Paragraph("Total Inadimplente: R$ " + cliente.getTotalInadimplente()));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Detalhamento por Cobranças:"));
        for (CobrancasFaturamento cob : cliente.getCobrancas()) {
            document.add(new Paragraph("ID: " + cob.getIdCobranca() +
                    " | Valor: R$ " + cob.getValorCobranca() +
                    " | Vencimento: " + cob.getVencimento() +
                    " | Status: " + cob.getStatus()));
        }

        document.close();
    }

}
