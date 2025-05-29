package com.hotelpet;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
    private Pet pet;
    private Servico servico;
    private LocalDate data;
    private LocalTime inicio;
    private LocalTime fim;

    public Agendamento(Pet pet, Servico servico, LocalDate data, LocalTime inicio) {
        this.pet = pet;
        this.servico = servico;
        this.data = data;
        this.inicio = inicio;
        this.fim = inicio.plus(servico.getDuracao());
    }

    public boolean conflitaCom(Agendamento outro) {
        if (!this.data.equals(outro.data)) return false;
        return this.inicio.isBefore(outro.fim) && this.fim.isAfter(outro.inicio);
    }

    public Pet getPet() {
        return pet;
    }

    public Servico getServico() {
        return servico;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    @Override
    public String toString() {
        return "Pet: " + pet.getNome() + ", Serviço: " + servico.getNome() +
               ", Data: " + data + ", Início: " + inicio + ", Fim: " + fim;
    }
}
