package view;

import java.util.InputMismatchException;
import java.util.Scanner;
public class MenuView {
    Scanner scanner = new Scanner(System.in);
    MenuTransacaoView menuTransacaoView = new MenuTransacaoView();
    MenuContaBancariaView menuContaBancariaView = new MenuContaBancariaView();
    public void menuAreas(){
        System.out.println("Digite uma das seguinte opções");
        System.out.println("1 - Área Conta bancária");
        System.out.println("2 - Área de transações");
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
            menuAreas();
            opcao = selecionarOpcao();
            switch (opcao) {
                case 1:
                    menuTransacaoView.opcoesTransacoes();
                    break;
                case 2:
                    menuContaBancariaView.opcoesContaBancaria();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        }while(opcao != 0);
    }
}
