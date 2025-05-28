package com.hotelpet;

import java.time.LocalDateTime;

public class Pet {
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private double peso;
    private Tutor tutor;
    private Plano plano;
    private LocalDateTime horaEntrada;

    public Pet(String nome, String especie, String raca, int idade, double peso, Tutor tutor, Plano plano) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
        this.tutor = tutor;
        this.plano = plano;
         this.horaEntrada = LocalDateTime.now();
    }

    @Override
    public String toString() {
    return "\n--- Pet ---" +
           "\nNome: " + nome +
           "\nEspécie: " + especie +
           "\nRaça: " + raca +
           "\nIdade: " + idade + " anos" +
           "\nPeso: " + peso + " kg" +
           "\nTutor: " + tutor.getNome() +
           "\nContato: " + tutor.getContato() +
           "\nPlano: " + plano.getClass().getSimpleName();
}


    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public double getPeso() {
        return peso;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Plano getPlano() {
        return plano;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    



}
