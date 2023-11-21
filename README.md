
---

# Sistema de Conta Bancária

Este é um sistema de conta bancária simples sem o uso de nenhum framework desenvolvido em Java. O sistema permite a realização de operações como criação de contas, depósitos, saques e consulta de transações. O código é dividido em classes para manter a organização e a reutilização de código.

## Configuração do Banco de Dados

Antes de usar o sistema, você deve configurar um banco de dados SQL e atualizar as informações de conexão no arquivo `ConexaoBanco.java`. Certifique-se de criar o banco de dados e as tabelas conforme as definições fornecidas no código.

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

### 1. Criar Conta Bancária

```java
public void inserirConta(int saldo, int cliente_id) {
    // Cria uma nova conta bancária com um número de conta aleatório.
}
```

### 2. Consultar Todas as Contas

```java
public void consultaTodasAsContas() {
    // Consulta todas as contas bancárias cadastradas no banco de dados.
}
```

### 3. Deletar Conta Bancária

```java
public void deletarConta(int id) {
    // Deleta uma conta bancária com base no ID fornecido.
}
```

### 4. Listar Transações de uma Conta

```java
public void listarTransacoesDaConta(int id) {
    // Lista todas as transações realizadas em uma determinada conta.
}
```

### 5. Realizar Depósito

```java
public void fazerDeposito(int idConta, BigDecimal valorDeposito) {
    // Realiza um depósito na conta bancária especificada.
}
```

### 6. Realizar Saque

```java
public void fazerSaque(int idConta, BigDecimal valorSaque) {
    // Realiza um saque na conta bancária especificada, verificando o saldo disponível.
}
```

### 7. Listar Contas de um Cliente

```java
public void listarContasDeUmCliente(int idCliente) {
    // Lista todas as contas associadas a um cliente específico.
}
```
---
# Criação das tabelas SQL
## Tabela Cliente
CREATE TABLE Cliente
- id INT PRIMARY KEY AUTO_INCREMENT,
- nome VARCHAR(255) NOT NULL,
- cpf VARCHAR(11) NOT NULL UNIQUE,
- endereco VARCHAR(255),

## Tabela ContaBancaria
CREATE TABLE ContaBancaria
- id INT PRIMARY KEY AUTO_INCREMENT,
- numero_conta VARCHAR(20) NOT NULL UNIQUE,
- saldo DECIMAL(10, 2) DEFAULT 0.0,
- cliente_id INT,
- FOREIGN KEY (cliente_id) REFERENCES Cliente(id)

## Tabela Transacao
CREATE TABLE Transacao

- id INT PRIMARY KEY AUTO_INCREMENT,
- data DATETIME NOT NULL,
- valor DECIMAL(10, 2) NOT NULL,
- conta_origem_id INT,
- conta_destino_id INT,
- FOREIGN KEY (conta_origem_id) REFERENCES ContaBancaria(id),
- FOREIGN KEY (conta_destino_id) REFERENCES ContaBancaria(id)
