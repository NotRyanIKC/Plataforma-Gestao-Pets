package com.hotelpet;

public class Standard implements Plano {
    public double calcularTarifaPorHora() {
        return 10.0;
    }

    public String getNomePlano() {
        return "Standard";
    }
}
