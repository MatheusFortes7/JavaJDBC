package PrimeiraQuestao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double desconto = 0.0;
        do {
            try {
                System.out.println("Entre com o desconto do produto: ");
                desconto = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um double.");
            }
            sc.nextLine();
        } while (desconto == 0.0);

        System.out.println("Double: "+ desconto);
    }
}
