# 🐾 Plataforma de Gestão de Pets - Hotel Pet

Este projeto é uma solução parcial para o desafio de digitalização de processos de um hotel pet de alto padrão, focando no **cadastro de pets via arquivo CSV** e em **testes unitários** para garantir a qualidade do código.

## ✅ Funcionalidades Implementadas

* 📅 **Importação de pets a partir de arquivos CSV**
* 🧪 **Testes unitários** utilizando biblioteca JUnit
* 🐶 Suporte a diferentes **planos de hospedagem**: Standard, Premium e VIP
* 🎈 Suporte a diferentes tipos de Serviços para o pet
* 📓 Relatório Mensal
* 👤 Cadastro de tutor vinculado ao pet
* ♻️ Estrutura orientada a objetos com uso de herança, polimorfismo e interfaces

## 🚀 Como Executar

1. Clone o repositório:
2. Compile o projeto com Maven:
3. Execute a aplicação (com uma classe principal 'App'):
4. Para rodar os testes:

   ```bash
   mvn test 
   ```

## 🧪 Tecnologias e Bibliotecas

* Java 17+
* Maven
* JUnit 5
* OpenCSV

## 📃 Formato do Arquivo CSV

Exemplo de entrada:

```
nome,especie,raca,idade,peso,tutor_nome,tutor_contato,hora_entrada,plano
Buddy,cachorro,Labrador,3,28.5,João Silva,11999999999,2025-05-28T10:00,VIP
```



