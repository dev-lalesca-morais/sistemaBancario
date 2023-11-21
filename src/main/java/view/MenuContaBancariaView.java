package view;

import service.ContaBancariaService;
import java.util.InputMismatchException;
import java.util.Scanner;
public class MenuContaBancariaView {
    Scanner scanner = new Scanner(System.in);
    ContaBancariaService contaBancariaService = new ContaBancariaService();
    public void menuContaBancaria(){
        System.out.println("Digite uma das seguinte opções");
        System.out.println("1 - Exibir todas as contas");
        System.out.println("2 - Cadastrar uma nova conta");
        System.out.println("3 - Deletar uma conta");
        System.out.println("4 - Voltar ao menu principal");
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
    public void opcoesContaBancaria() {
        int opcao;
        do {
            menuContaBancaria();
            opcao = selecionarOpcao();
            switch (opcao) {
                case 1:
                    contaBancariaService.consultaTodasAsContas();
                    break;
                case 2:
                    System.out.println("Digite o saldo que deseja adicionar na conta");
                    int saldo = scanner.nextInt();
                    System.out.println("Digite o ID do cliente que deseja adicionar na conta");
                    int cliente_id = scanner.nextInt();
                    contaBancariaService.inserirConta(saldo, cliente_id);
                    break;
                case 3:
                    System.out.println("Digite o ID da conta que deseja deletar: ");
                    int idConta = scanner.nextInt();
                    contaBancariaService.deletarConta(idConta);
                    break;
                case 4:
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