package com.hotelpet;

import java.time.Duration;

public class Servico {
    private String nome;
    private Duration duracao;
    private double precoBase;

    public Servico(String nome, Duration duracao, double precoBase) {
        this.nome = nome;
        this.duracao = duracao;
        this.precoBase = precoBase;
    }

    public String getNome() {
        return nome;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    @Override
    public String toString() {
        return nome + " - " + duracao.toMinutes() + " min - R$" + precoBase;
    }
}
