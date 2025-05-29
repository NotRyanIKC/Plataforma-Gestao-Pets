package com.hotelpet;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaServicos {
    private List<Agendamento> agendamentos = new ArrayList<>();
    private static final LocalTime ABERTURA = LocalTime.of(10, 0);
    private static final LocalTime FECHAMENTO = LocalTime.of(22, 0);

    public boolean agendar(Agendamento novo) {
        if (novo.getInicio().isBefore(ABERTURA) || novo.getFim().isAfter(FECHAMENTO)) {
            System.out.println("Horário fora do expediente: 10h às 22h.");
            return false;
        }

        for (Agendamento ag : agendamentos) {
            if (ag.getPet().equals(novo.getPet()) && ag.conflitaCom(novo)) {
                System.out.println("Conflito de horário para " + novo.getPet().getNome());
                return false;
            }
        }

        agendamentos.add(novo);
        System.out.println("Agendado com sucesso: " + novo);
        return true;
    }

    public void listarAgendamentos() {
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento.");
            return;
        }

        for (Agendamento ag : agendamentos) {
            System.out.println(ag);
        }
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }
}
