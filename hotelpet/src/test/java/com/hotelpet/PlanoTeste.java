package com.hotelpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlanoTeste {

@Test
public void deveRetornarTarifaStandard() {
    Plano plano = new Standard();
    assertEquals(10.0, plano.calcularTarifaPorHora());
    assertEquals("Standard", plano.getNomePlano());
    assertEquals(20.0, plano.calcularTarifaPorHora());
    assertEquals("Premium", plano.getNomePlano());
    assertEquals(30.0, plano.calcularTarifaPorHora());
    assertEquals("Vip", plano.getNomePlano());

}


}
