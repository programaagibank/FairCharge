package br.com.agi.model;

import java.util.Date;

public interface Taxa {
    String getTipo();
    int getID();
    double getPercentual();
    Date getDataCriacao();
}
