package br.com.agi.utils;
import javafx.scene.control.Label;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatoMonetarioFX {

    public void exibirValorFormatado(Label labelValor, double valor) {
        NumberFormat formatoBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        labelValor.setText(formatoBR.format(valor));
    }

}
