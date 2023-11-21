package service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TransacaoService {
    private Statement statement;
    private Connection connection;
    public TransacaoService() {
    }
    public TransacaoService(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }
    public void listarTransacoesDaConta(int id) {
        try {
            String sql = "SELECT * FROM Transacao WHERE conta_origem_id = ? OR conta_destino_id = ?";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                System.out.println("===== TRANSAÇÕES DA CONTA =====");
                System.out.println("ID da Conta: " + resultSet.getInt("id") +
                        " | Data: " + resultSet.getString("data") +
                        " | Valor: " + resultSet.getBigDecimal("valor") +
                        " | Conta de origem: " + resultSet.getInt("conta_origem_id") +
                        " | Conta de destino: " + resultSet.getInt("conta_destino_id"));
            } else {
                System.out.println("Conta com ID " + id + " não encontrada, tente novamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void fazerDeposito(int idConta, BigDecimal valorDeposito) {
        try {
            String atualizaSaldoSql = "UPDATE ContaBancaria SET saldo = saldo + ? WHERE id = ?";
            int rowsUpdated = statement.executeUpdate(atualizaSaldoSql);
            if (rowsUpdated > 0) {
                System.out.println("Depósito de " + valorDeposito + " realizado com sucesso na conta " + idConta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void fazerSaque(int idConta, BigDecimal valorSaque) {
        try {
            String verificaSaldoSql = "SELECT saldo FROM ContaBancaria WHERE id = ?";
            BigDecimal saldoAtual = BigDecimal.ZERO;

            try {
                ResultSet resultSet = statement.executeQuery(verificaSaldoSql);
                if (resultSet.next()) {
                    saldoAtual = resultSet.getBigDecimal("saldo");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (saldoAtual.compareTo(valorSaque) >= 0) {
                String atualizaSaldoSql = "UPDATE ContaBancaria SET saldo = saldo - ? WHERE id = ?";

                int rowsUpdated = statement.executeUpdate(atualizaSaldoSql);
                if (rowsUpdated > 0) {
                    System.out.println("Saque de " + valorSaque + " realizado com sucesso na conta " + idConta);
                }
            } else {
                System.out.println("Saldo insuficiente na conta " + idConta + " para realizar o saque de " + valorSaque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listarContasDeUmCliente(int idCliente) {
        try {
            String sql = "SELECT * FROM Transacao WHERE cliente_id = " + idCliente;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                System.out.println("===== CONTAS DO CLIENTE =====");
                System.out.println("ID da Conta: " + resultSet.getInt("id") +
                        " | Data: " + resultSet.getString("data") +
                        " | Nome: " + resultSet.getBigDecimal("nome") +
                        " | Conta de origem: " + resultSet.getInt("conta_origem_id ") +
                        " | Conta de destino: " + resultSet.getInt("conta_destino_id "));
            } else {
                System.out.println("Conta com ID " + idCliente + " não encontrada, tente novamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
