package view;

import service.TransacaoService;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTransacaoView {
    Scanner scanner = new Scanner(System.in);
    TransacaoService transacaoService = new TransacaoService();
    public void menuTransacao(){
        System.out.println("Digite uma das seguinte opções");
        System.out.println("1 - Listar transações de uma conta");
        System.out.println("2 - Fazer depósito");
        System.out.println("3 - Fazer saque");
        System.out.println("4 - Listar contas de um cliente");
        System.out.println("5 - Voltar ao menu principal");
        System.out.println("0 - Sair.");
    }
    public int selecionarOpcao(){
        try{
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        return 1;
    }
    public void opcoesTransacoes() {
        int opcao;
        do {
            menuTransacao();
            opcao = selecionarOpcao();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o ID da conta que deseja listar");
                    int idConta = scanner.nextInt();
                    transacaoService.listarTransacoesDaConta(idConta);
                    break;
                case 2:
                    System.out.println("Digite o ID da conta que deseja depositar");
                    int idContaDeposito = scanner.nextInt();
                    System.out.println("Digite o valor de depósito: ");
                    BigDecimal valorDeposito = scanner.nextBigDecimal();
                    transacaoService.fazerDeposito(idContaDeposito, valorDeposito);
                    break;
                case 3:
                    System.out.println("Digite o ID da conta que deseja sacar");
                    int idContaSaque = scanner.nextInt();
                    System.out.println("Digite o valor de saque: ");
                    BigDecimal valorSaque = scanner.nextBigDecimal();
                    transacaoService.fazerSaque(idContaSaque, valorSaque);
                    break;
                case 4:
                    System.out.println("Digite o ID da conta: ");
                    int idContaCliente = scanner.nextInt();
                    transacaoService.listarContasDeUmCliente(idContaCliente);
                    break;
                case 5:
                    System.out.println("Retornando ao menu principal...");
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        } while (opcao != 0);
    }
}