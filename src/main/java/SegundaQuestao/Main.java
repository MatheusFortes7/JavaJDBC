package SegundaQuestao;

import SegundaQuestao.Factory.ConectionFactory;
import SegundaQuestao.DAO.EmocoesDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        System.out.println("Favor entrar com a frase desejada: ");
        String linha = "";
        do{
            linha = sc.nextLine();
        } while(linha.isEmpty() || linha.isBlank());


        int qntdFeliz = getQuantidade(":-)", linha);
        int qntdTriste = getQuantidade(":-(", linha);


        System.out.println("\nA frase: " + linha + " contem  :-( (chateado) " + qntdTriste + " vezes");
        System.out.println("A frase: " + linha + " contem  :-) (divertido) " + qntdFeliz + " vezes\n");
        String resultado = "";

        if(qntdFeliz > qntdTriste){
            System.out.println("divertido");
            resultado = "divertido";
        } else if( qntdTriste > qntdFeliz){
            System.out.println("Chateado");
            resultado = "chateado";
        } else {
            System.out.println("neutro");
            resultado = "neutro";
        }

        try (Connection connection = new ConectionFactory().recuperarConexao()){
            EmocoesDAO emocoesDAO = new EmocoesDAO(connection);

            emocoesDAO.incluir(resultado);
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
        }


    }

    private static int getQuantidade(String sentimento, String linha) {

        int qntdSentimento = 0;
        for (int i = 0; i < linha.length(); i++) {
            if (linha.substring(i).startsWith(sentimento)) {
                qntdSentimento ++;
            }
        }
        return qntdSentimento;
    }
}
