package com.hotelpet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Hotel {
    private List<Pet> pets = new ArrayList<>();
    private Scanner scanf = new Scanner(System.in);
    private CatalogoServicos catalogoServicos = new CatalogoServicos();
    private AgendaServicos agenda = new AgendaServicos();

    public static void main(String[] args) {
        new Hotel().executarMenu();
        
    }
   


    public void executarMenu() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== Hotel Pet ===");
            System.out.println("1. Cadastrar pet");
            System.out.println("2. Listar pets");
            System.out.println("3. Exportar pets para CSV");
            System.out.println("4. Importa pets CSV");
            System.out.println("5. Agendar serviço");
            System.out.println("6. Dar saida no Pet");
            System.out.println("7. Relatorio Mensal");
            System.out.println("8. Sair");
            System.out.print("Escolha: ");
            int opcao = scanf.nextInt();
            scanf.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarPet();
                    break;
                case 2:
                    listarPets();
                    break;
                case 3:
                    exportarPetsParaCSV();
                    break;
                case 4:
                    importarPetsDeCSV();
                    break;
                case 5:
                    agendarServico();
                    break;
                case 6:
                    darSaida();
                    break;
                case 7:
                    relatorioMensal();
                    break;
                case 8:
                    System.out.println("Encerrando...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

public void importarPetsDeCSV() {
    try (BufferedReader reader = new BufferedReader(new FileReader("pets.csv"))) {
        String linha;
        boolean primeiraLinha = true;

        while ((linha = reader.readLine()) != null) {
            if (primeiraLinha) {
                primeiraLinha = false;
                continue;
            }
            if (linha.trim().isEmpty()) {
                continue;
            }

            String[] partes = linha.trim().split("\\s*,\\s*");
            if (partes.length < 8) {
                System.out.println("Linha inválida: " + linha);
                continue;
            }

            try {
                String nome = partes[0];
                String especie = partes[1];
                String raca = partes[2];
                int idade = Integer.parseInt(partes[3]);
                double peso = Double.parseDouble(partes[4]);
                String nomeTutor = partes[5];
                String contatoTutor = partes[6];
                String tipoPlano = partes[7];
                

                Tutor tutor = new Tutor(nomeTutor, contatoTutor);
                Plano plano;
                switch (tipoPlano.toLowerCase()) {
                    case "standard": plano = new Standard(); break;
                    case "premium": plano = new Premium(); break;
                    case "vip": plano = new Vip(); break;
                    default: plano = new Standard(); break;
                }

                Pet pet = new Pet(nome, especie, raca, idade, peso, tutor, plano);

               

                pets.add(pet);
            } catch (NumberFormatException e) {
                System.out.println("Erro de formatação numérica: " + linha);
            } catch (Exception e) {
                System.out.println("Erro inesperado na linha: " + linha);
            }
        }

        System.out.println("Importação concluída com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao importar pets: " + e.getMessage());
    }
}


public void exportarPetsParaCSV() {
    try (FileWriter writer = new FileWriter("pets.csv")) {
        writer.write("Nome,Especie,Raca,Idade,Peso,Tutor,Contato,Plano\n");
        for (Pet pet : pets) {
            writer.write(String.format("%s,%s,%s,%d,%.2f,%s,%s,%s,\n",
                    pet.getNome(),
                    pet.getEspecie(),
                    pet.getRaca(),
                    pet.getIdade(),
                    pet.getPeso(),
                    pet.getTutor().getNome(),
                    pet.getTutor().getContato(),
                    pet.getPlano().getClass().getSimpleName()
                    
            ));
        }
        System.out.println("Pets exportados para pets.csv com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao exportar pets: " + e.getMessage());
    }
}


    private void cadastrarPet() {
        System.out.print("Nome do pet: ");
        String nome = scanf.nextLine();

        System.out.print("Espécie: ");
        String especie = scanf.nextLine();

        System.out.print("Raça: ");
        String raca = scanf.nextLine();

        System.out.print("Idade: ");
        int idade = scanf.nextInt();
        scanf.nextLine();

        System.out.print("Peso: ");
        double peso = scanf.nextDouble();
        scanf.nextLine();

        System.out.print("Nome do tutor: ");
        String nomeTutor = scanf.nextLine();

        System.out.print("Contato do tutor: ");
        String contatoTutor = scanf.nextLine();

        Tutor tutor = new Tutor(nomeTutor, contatoTutor);

        System.out.println("Escolha o plano: [1] Standard, [2] Premium, [3] VIP");
        int tipoPlano = scanf.nextInt();
        scanf.nextLine();

        Plano plano;

        switch (tipoPlano) {

            case 1:
                plano = new Standard();
                break;
            case 2:
                plano = new Premium();
                break;
            case 3:
                plano = new Vip();
                break;
            default:
                System.out.println("Plano inválido. Usando Standard.");
                plano = new Standard();
}


        Pet pet = new Pet(nome, especie, raca, idade, peso, tutor, plano);
        pets.add(pet);
        System.out.println("Pet cadastrado com sucesso!");
        System.out.println("Hora de entrada: "+ pet.getHoraEntrada());
    }

    private void listarPets() {
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }

        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

    public Pet buscarPetPorNome(String nome) {
    for (Pet pet : pets) {
        if (pet.getNome().equalsIgnoreCase(nome)) {
            return pet;
        }
    }
    return null; 
}

public void darSaida() {

    System.out.print("Nome do pet para dar saída: ");
    String nome = scanf.nextLine();
    Pet pet = buscarPetPorNome(nome);

    if (pet == null) {
        System.out.println("Pet não encontrado.");
        return;
    }

    
    LocalDateTime entrada = pet.getHoraEntrada();
    System.out.println("Digite o Horario de Saída: (HH:mm)");
    String horaSaidaStr = scanf.nextLine();
     LocalDateTime saida;
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horaSaida = LocalTime.parse(horaSaidaStr, formatter);
        saida = LocalDateTime.of(entrada.toLocalDate(), horaSaida);

        // Se a hora de saída for anterior à entrada, assume que foi no dia seguinte
        if (saida.isBefore(entrada)) {
            saida = saida.plusDays(1);
        }
    } catch (DateTimeParseException e) {
        System.out.println("Formato de hora inválido. Use o formato HH:mm.");
        return;
    }

    Duration duracao = Duration.between(entrada, saida);
    double horas = duracao.toMinutes() / 60.0;

    double tarifaHora = pet.getPlano().calcularTarifaPorHora();
    double valorHospedagem = tarifaHora * horas;

    double valorServicos = 0.0;
    System.out.println("\n--- FATURA DETALHADA ---");
    System.out.println("Pet: " + pet.getNome());
    System.out.println("Plano: " + pet.getPlano().getNomePlano() + " (R$" + tarifaHora + "/hora)");
    System.out.println("Entrada: " + entrada);
    System.out.println("Saída: " + saida);
    System.out.printf("Tempo hospedado: %.2f horas%n", horas);
    System.out.printf("Valor da hospedagem: R$ %.2f%n", valorHospedagem);

    System.out.println("\nServiços utilizados:");
    for (Agendamento ag : agenda.getAgendamentos()) {
        if (ag.getPet().equals(pet)) {
            double preco = ag.getServico().getPrecoBase(); 
            valorServicos += preco;
            System.out.println("- " + ag.getServico().getNome() + " em " + ag.getData() + " às " + ag.getInicio() + " (R$" + preco + ")");
        }
    }

    System.out.printf("\nTotal de serviços: R$ %.2f%n", valorServicos);
    System.out.printf("TOTAL GERAL: R$ %.2f%n", (valorHospedagem + valorServicos));


    pets.remove(pet);
}


   public void agendarServico() {

    System.out.print("Nome do pet: ");
    String nomePet = scanf.nextLine();
    Pet pet = buscarPetPorNome(nomePet);

    if (pet == null) {
        System.out.println("Pet não encontrado.");
        executarMenu();
    }

    System.out.print("Nome do serviço: ");
    String nomeServico = scanf.nextLine();
    Servico servico = catalogoServicos.buscarServicoPorNome(nomeServico);

    if (servico == null) {
        System.out.println("Serviço não encontrado.");
        return;
    }

    System.out.print("Data (AAAA-MM-DD): ");
    LocalDate data = LocalDate.parse(scanf.nextLine());

    System.out.print("Hora de início (HH:MM): ");
    LocalTime inicio = LocalTime.parse(scanf.nextLine());

    Agendamento novo = new Agendamento(pet, servico, data, inicio);
    agenda.agendar(novo);
    executarMenu();
}
    public void relatorioMensal() {
    System.out.println("\n=== RELATÓRIO MENSAL ===");

    // Filtrar mês atual
    LocalDate hoje = LocalDate.now();
    int mesAtual = hoje.getMonthValue();
    int anoAtual = hoje.getYear();

    // 1. Pets atendidos (sem repeti-los)
    Set<Pet> petsAtendidos = new HashSet<>();

    // 2. Contador de serviços
    Map<String, Integer> servicosMaisUsados = new HashMap<>();

    // 3. Receita por serviço
    Map<String, Double> receitaPorServico = new HashMap<>();

    for (Agendamento ag : agenda.getAgendamentos()) {
        LocalDate data = ag.getData();
        if (data.getMonthValue() == mesAtual && data.getYear() == anoAtual) {
            petsAtendidos.add(ag.getPet());

            String nomeServico = ag.getServico().getNome();
            double preco = ag.getServico().getPrecoBase();

            // Contar serviços
            servicosMaisUsados.put(nomeServico,
                servicosMaisUsados.getOrDefault(nomeServico, 0) + 1);

            // Soma da receita
            receitaPorServico.put(nomeServico,
                receitaPorServico.getOrDefault(nomeServico, 0.0) + preco);
        }
    }

    System.out.println("\nPets Atendidos:");
    if (petsAtendidos.isEmpty()) {
        System.out.println("- Nenhum pet atendido este mês.");
    } else {
        for (Pet pet : petsAtendidos) {
            System.out.println("- " + pet.getNome() + " (" + pet.getEspecie() + ")");
        }
    }

    System.out.println("\nServiços mais utilizados:");
    if (servicosMaisUsados.isEmpty()) {
        System.out.println("- Nenhum serviço registrado este mês.");
    } else {
        servicosMaisUsados.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue() - e1.getValue())
            .forEach(entry -> System.out.println("- " + entry.getKey() + ": " + entry.getValue() + "x"));
    }

    System.out.println("\nReceita total por serviço:");
    if (receitaPorServico.isEmpty()) {
        System.out.println("- Nenhuma receita registrada.");
    } else {
        for (Map.Entry<String, Double> entry : receitaPorServico.entrySet()) {
            System.out.printf("- %s: R$ %.2f\n", entry.getKey(), entry.getValue());
        }
    }
}

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Scanner getScanf() {
        return scanf;
    }

    public void setScanf(Scanner scanf) {
        this.scanf = scanf;
    }
}
