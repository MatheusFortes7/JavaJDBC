package PrimeiraQuestao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        int ano = 0;
        do {
            try {
                System.out.println("Entre com o ano do inicio da oferta: ");
                ano = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um int.");
            }
            sc.nextLine();
        } while (ano == 0);
        String ano2 = String.valueOf(ano);
        if(ano2.length() != 4){
            System.out.println("Favor entrar com um numero de ano correto.");
            do {
                try {
                    System.out.println("Entre com o desconto do produto: ");
                    ano = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Favor entrar com um double.");
                }
                sc.nextLine();
            } while (ano == 0);
        }

        int mes = 0;
        do {
            try {
                System.out.println("Entre com o mes do inicio da oferta: ");
                mes = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um double.");
            }
            sc.nextLine();
        } while (mes == 0);
        if(mes < 0 && mes < 12){
            System.out.println("Favor entrar com um numero de mes correto.");
            do {
                try {
                    System.out.println("Entre com o numero do dia de inicio da oferta: ");
                    mes = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Favor entrar com um double.");
                }
                sc.nextLine();
            } while (mes == 0);
        }

        int dia = 0;
        do {
            try {
                System.out.println("Entre com o dia do inicio da oferta: ");
                dia = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um double.");
            }
            sc.nextLine();
        } while (dia == 0);
        if(dia < 0 && dia < 31){
            System.out.println("Favor entrar com um numero de dia correto.");
            do {
                try {
                    System.out.println("Entre com o numero do dia de inicio da oferta: ");
                    dia = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Favor entrar com um double.");
                }
                sc.nextLine();
            } while (dia == 0);
        }
        Date data = null;
        String dataFinal = ano+"-"+mes+"-"+dia;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            data = sdf.parse(dataFinal);
        } catch (ParseException e) {
            System.out.println("Erro de conversao de dados");
        }

        System.out.println(sdf.format(data));




    }
}
