import models.ConversorDeMoedas;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        ConversorDeMoedas conversor = new ConversorDeMoedas();

        while   (true) {
            try {

                conversor.exibeMensagemBoasVindas();
                conversor.opcaoMenu(scanner.nextInt());
                conversor.mensagemValor();
                conversor.recebeValor(scanner.nextDouble());
                conversor.chamaApi();

            } catch (InputMismatchException e) {
                System.out.println("Preencha os campso corretamente");

                scanner.nextLine();
                continue;
            }

            scanner.nextLine();
            conversor.sair();
            conversor.opcaoDeSaida(scanner.nextLine());


        }

    }
}
