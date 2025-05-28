package com.hotelpet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Hotel {
    private List<Pet> pets = new ArrayList<>();
    private Scanner scanf = new Scanner(System.in);

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
            System.out.println("5. Sair");
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

            String[] partes = linha.split(",");
            if (partes.length < 8) {
                System.out.println("Linha inválida: " + linha);
                continue;
            }

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

            switch (tipoPlano.trim().toLowerCase()) {
                case "standard":
                    plano = new Standard();
                    break;
                case "premium":
                    plano = new Premium();
                    break;
                case "vip":
                    plano = new Vip();
                    break;
                default:
                    plano = new Standard();
                    break;
            }

            Pet pet = new Pet(nome, especie, raca, idade, peso, tutor, plano);
            pets.add(pet);
        }

        System.out.println("Importação concluída com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao importar pets: " + e.getMessage());
    }
}

public void exportarPetsParaCSV() {
    try (FileWriter writer = new FileWriter("pets.csv")) {
        writer.write("Nome,Espécie,Raça,Idade,Peso,Tutor,Contato,Plano\n");
        for (Pet pet : pets) {
            writer.write(String.format("%s,%s,%s,%d,%.2f,%s,%s,%s\n",
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
