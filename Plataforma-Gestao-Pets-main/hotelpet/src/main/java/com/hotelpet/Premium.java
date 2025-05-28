package com.hotelpet;

public class Premium implements Plano {
    public double calcularTarifaPorHora() {
        return 20.0;
    }

    public String getNomePlano() {
        return "Premium";
    }
}
