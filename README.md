# Trabalho 1

## Compilador Didático

Neste projeto de compilador didático, foram realizadas implementações incrementais para aprimorar suas funcionalidades. A seguir, descrevemos as principais modificações realizadas em cada exercício:

### Exercício 1: Números com mais de um dígito
No primeiro exercício, aprimoramos o compilador para aceitar números naturais com mais de um dígito. Isso expandiu a capacidade do compilador para lidar com expressões mais complexas.

### Exercício 2: Operações de subtração e divisão
No segundo exercício, adicionamos suporte para operações de subtração e divisão ao compilador. As operações de subtração foram implementadas através da instrução SUB, enquanto as operações de divisão foram implementadas com a instrução DIV.

### Exercício 3: Back-end Interpretador
No terceiro exercício, realizamos uma modificação significativa no compilador ao substituir o back-end existente por um back-end interpretador. Agora, em vez de gerar uma String com código para ser executado na máquina de pilha, o back-end retorna diretamente o resultado da computação da expressão de entrada. Isso simplifica o processo e melhora a eficiência.

### Exercício 4: Máquina de Pilha em Java
No último exercício, implementamos uma máquina de pilha em Java capaz de executar as instruções geradas pelo compilador. A máquina de pilha recebe um arquivo de texto contendo as instruções como entrada e fornece o resultado final da execução dessas instruções como saída. A classe principal foi denominada MaquinaPilha, e um exemplo de uso foi fornecido:

## Executando

Para compilar e executar utilizar os seguintes comandos:

   ```bash
      javac *.java
      java MaquinaPilha arquivoDeEntrada
   ````
# Trabalho 2

## Analisador Léxico para a Linguagem Lugosi

Este projeto tem como objetivo a implementação de um analisador léxico para a Linguagem Lugosi, utilizando a ferramenta JavaCC. O analisador léxico é responsável por realizar a análise dos tokens presentes no código fonte, identificando palavras-chave, operadores, identificadores, literais, entre outros elementos léxicos da linguagem.

## Executando o Analisador LéxicoParser

   ```bash
      javacc Lugosi.jj
      javac *.java
      java Lugosi teste
   ```


## Parser para a Linguagem Lugosi

## Descrição do Projeto

O objetivo deste projeto é implementar um parser (Analisador Sintático) para a linguagem Lugosi. O parser será construído em cima do analisador léxico proposto no trabalho anterior. Deve-se observar que o Javacc não aceita recursão à esquerda nem alternativas começando com um mesmo símbolo. Portanto, é crucial eliminar essas construções da gramática, caso existam.

## Requisitos do Projeto

- Implementar um parser para a linguagem Lugosi.
- Utilizar o analisador léxico desenvolvido no trabalho anterior como base.
- Eliminar recursão à esquerda e alternativas começando com um mesmo símbolo da gramática para garantir a compatibilidade com o Javacc.

## Configuração do Ambiente

1. **Requisitos do Sistema:**
   Certifique-se de ter o Java instalado em sua máquina, pois o Javacc é uma ferramenta Java.

2. **Clonando o Repositório:**
   ```bash
   git clone https://github.com/brcsilveira/Projeto-de-Compiladores.git
   cd nome-do-repositorio

## Executando o Parser

   ```bash
   javacc Lugosi.jj
   javac *.java
   java Lugosi teste
   ```

# Nome do Seu Projeto

Breve descrição ou propósito do seu projeto.

## Autores

- [Bruno Chim Silveira](https://github.com/brcsilveira)
- [Ricardo Coutinho Cordeiro](https://github.com/RegretCode)

## Licença

Este projeto está licenciado sob a Licença MIT - consulte o arquivo [LICENSE](https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt) para obter detalhes.
