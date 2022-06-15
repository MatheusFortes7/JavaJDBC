package PrimeiraQuestao;

import PrimeiraQuestao.DAO.ProdutoDAO;
import PrimeiraQuestao.Factory.ConectionFactory;
import PrimeiraQuestao.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        int id;
        Produto produto = new Produto();
        try (Connection connection = new ConectionFactory().recuperarConexao()){
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);

            do {
                System.out.println("======== Seja bem vindo ao sistema ========");
                System.out.println("1-  Inserir uma nova oferta.");
                System.out.println("2-  Atualizar uma oferta existente.");
                System.out.println("3-  Excluir uma oferta.");
                System.out.println("4 -  Listar as ofertas de um produto.");
                System.out.println("0 - Sair...");
                System.out.println("Digite a opção desejada: ");
                try {
                    opcao = Integer.valueOf(sc.nextLine());
                } catch (NumberFormatException e) {
                    opcao = -1;
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Voce selecionou a opção: Inserir uma nova oferta.");
                        //chamar dao de inserção
                        break;
                    case 2:
                        System.out.println("Voce selecionou a opção: Atualizar uma oferta existente.");
                        id= 0;
                        do {
                            try {
                                System.out.println("Favor entrar com o id da oferta que voçê deseja atualizar: ");
                                id = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Favor entrar com um número inteiro.");
                            }
                            sc.nextLine();
                        } while (id == 0);

                        incluirInformacoesProduto(produto, id);

                        produtoDAO.atualizar(produto);
                        break;
                    case 3:
                        System.out.println("Voce selecionou a opção: Excluir uma oferta.");
                        id= 0;
                        do {
                            try {
                                System.out.println("Favor entrar com o id da oferta que voçê deseja excluir: ");
                                id = sc.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Favor entrar com um número inteiro.");
                            }
                            sc.nextLine();
                        } while (id == 0);
                        System.out.println("O id deletado será "+ id);

                        produtoDAO.excluir(id);

                        break;
                    case 4:
                        System.out.println("Voce selecionou a opção: Listar ofertas de um produto");
                        System.out.println("Favor entrar com o nome do produto que voçê deseja ver: ");
                        //chamar dao de select
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida! Favor entrar com uma opção existente.");
                }

            } while (opcao != 0);


        } catch (SQLException e) {
            System.out.println("Conexão nao feita");
        }

    }

    private static void incluirInformacoesProduto(Produto produto, int id) {

        Scanner sc = new Scanner(System.in);
        String nome = "";
        while(nome.isBlank() || nome.isEmpty()){
            System.out.println("Entre com o nome do produto: ");
            nome = sc.nextLine();
        }

        String descricao = "";
        while(descricao.isBlank() || descricao.isEmpty()){
            System.out.println("Entre com o nome do produto: ");
            descricao = sc.nextLine();
        }

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

        double preco = 0.0;
        do {
            try {
                System.out.println("Entre com o preco do produto: ");
                preco = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um double.");
            }
            sc.nextLine();
        } while (preco == 0.0);

        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setDesconto(desconto);
        produto.setPreco(preco);
        // TODO: 15/06/2022
        //produto.setDataInicio(data);
    }
}
