package com.hotelpet;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class CatalogoServicos {
    private List<Servico> servicos;

    public CatalogoServicos() {
        servicos = new ArrayList<>();
        servicos.add(new Servico("Banho", Duration.ofMinutes(30), 30.0));
        servicos.add(new Servico("Tosa", Duration.ofMinutes(45), 50.0));
        servicos.add(new Servico("Passeio", Duration.ofMinutes(20), 20.0));
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void listarServicos() {
        for (Servico s : servicos) {
            System.out.println(s);
        }
    }

    public Servico buscarServicoPorNome(String nome) {
        for (Servico s : servicos) {
            if (s.getNome().equalsIgnoreCase(nome)) {
                return s;
            }
        }
        return null;
    }
}

