package service;

import connection.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ContaBancariaService {
    private Statement statement;
    private Conexao conexao;
    Random random = new Random();
    public ContaBancariaService() {
    }
    public ContaBancariaService(Conexao conexao) {
        this.conexao = conexao;
        try {
            statement = conexao.fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirConta(int saldo, int cliente_id) {
        int numero_conta = 10000 + random.nextInt(90000);

        String sql = "INSERT INTO ContaBancaria (numero_conta, saldo, cliente_id) " +
                "VALUES (" + numero_conta + ", " + saldo + ", " + cliente_id + ")";
        try {
            statement.executeUpdate(sql);
            System.out.println("Conta adicionada com sucesso no banco!");
            System.out.println("Número da conta: " + numero_conta);
            System.out.println("======================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultaTodasAsContas(){
        String sql = "SELECT * FROM ContaBancaria";
        boolean contasEncontrados = false;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                contasEncontrados = true;
                System.out.println("ID: " + resultSet.getInt("id") +
                        " | Número da conta: " + resultSet.getString("numero_conta"));
            }
            if (!contasEncontrados) {
                System.out.println("Ainda não foi cadastrada nenhuma conta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletarConta(int id){
        String sql = "DELETE FROM ContaBancaria WHERE id = " + id;
        try{
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0){
                System.out.println("Conta " + id + " foi deletada com sucesso!");
            }else{
                System.out.println("Conta " + id + " não encontrada!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
