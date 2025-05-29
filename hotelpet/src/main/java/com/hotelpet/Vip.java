package com.hotelpet;

public class Vip implements Plano {
    public double calcularTarifaPorHora() {
        return 30.0;
    }

    public String getNomePlano() {
        return "VIP";
    }
}
