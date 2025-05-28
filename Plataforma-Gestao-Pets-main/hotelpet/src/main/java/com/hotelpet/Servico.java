package com.hotelpet;

import java.util.Scanner;

public class Servico {

    private Scanner scanf = new Scanner(System.in);
    




    public void AgendarServico() {
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== Agendar Serviços ===");
            System.out.println("1. Banho");
            System.out.println("2. Tosa");
            System.out.println("3. Passeio");
            System.out.println("4. Alimentação Especial");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");
            int opcao = scanf.nextInt();
            scanf.nextLine();

            switch (opcao) {
                case 1:
                    agendarBanho();;
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    System.out.println("Voltando...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }  
    
    public void agendarBanho(){
        
        
System.out.println("Insira o Horário que você quer agendar");
        String horaAgendada = scanf.next();
        scanf.nextLine();
        
        System.out.println("banho agendado");


    }
} 