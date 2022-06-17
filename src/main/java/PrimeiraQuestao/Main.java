package PrimeiraQuestao;

import PrimeiraQuestao.DAO.ProdutoDAO;
import PrimeiraQuestao.Factory.ConectionFactory;
import PrimeiraQuestao.modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int opcao;
        int id;
        Produto produto = new Produto();
        Connection connection = null;
        try {
            connection = new ConectionFactory().recuperarConexao();
        } catch (SQLException e) {
            System.out.println("Erro de conexão");
        }
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);

        do {
            System.out.println("======== Seja bem vindo ao sistema ========");
            System.out.println("1 - Inserir uma nova oferta.");
            System.out.println("2 - Atualizar uma oferta existente.");
            System.out.println("3 - Excluir uma oferta.");
            System.out.println("4 - Listar as ofertas de um produto.");
            System.out.println("0 - Sair...");
            System.out.println("===========================================");
            System.out.println("Digite a opção desejada: ");
            try {
                opcao = Integer.valueOf(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    System.out.println("Voce selecionou a opção: Inserir uma nova oferta.");
                    id= 0;
                    do {
                        try {
                            System.out.println("Favor entrar com o id da oferta que voçê deseja inserir: ");
                            id = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Favor entrar com um número inteiro.\n");
                        }
                        sc.nextLine();
                    } while (id == 0);

                    incluirInformacoesProduto(produto, id);
                    produtoDAO.incluir(produto);
                    break;
                case 2:
                    System.out.println("Voce selecionou a opção: Atualizar uma oferta existente.");
                    id= 0;
                    do {
                        try {
                            System.out.println("Favor entrar com o id da oferta que voçê deseja atualizar: ");
                            id = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Favor entrar com um número inteiro.\n");
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
                            System.out.println("Favor entrar com um número inteiro.\n");
                        }
                        sc.nextLine();
                    } while (id == 0);
                    System.out.println("O id deletado será "+ id);

                    produtoDAO.excluir(id);

                    break;
                case 4:
                    System.out.println("Voce selecionou a opção: Listar ofertas de um produto");
                    String nomeProduto = "";
                    while(nomeProduto.isBlank() || nomeProduto.isEmpty()){
                        System.out.println("Favor entrar com o nome do produto que voçê deseja ver: ");
                        nomeProduto = sc.nextLine();
                    }
                    nomeProduto = "%"+nomeProduto+"%";
                    List<Produto> produtosExistentes =  produtoDAO.listar(nomeProduto);
                    produtosExistentes.stream().forEach(lp -> System.out.println(lp));
                    if(produtosExistentes.size() == 0){
                        System.out.println("\nNenhum produto encontrado\n");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida! Favor entrar com uma opção existente.\n");
            }

        } while (opcao != 0);

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
            System.out.println("Entre com a descricao do produto: ");
            descricao = sc.nextLine();
        }

        double desconto = 0.0;
        do {
            try {
                System.out.println("Entre com o desconto do produto: ");
                desconto = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um double.\n");
            }
            sc.nextLine();
        } while (desconto == 0.0);

        double preco = 0.0;
        do {
            try {
                System.out.println("Entre com o preco do produto: ");
                preco = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um double.\n");
            }
            sc.nextLine();
        } while (preco == 0.0);

        int ano = 0;
        do {
            try {
                System.out.println("Entre com o ano do inicio da oferta: ");
                ano = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um int.\n");
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
                    System.out.println("Favor entrar com um double.\n");
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
                System.out.println("Favor entrar com um double.\n");
            }
            sc.nextLine();
        } while (mes == 0);
        if(mes < 0 && mes > 12){
            System.out.println("Favor entrar com um numero de mes correto.");
            do {
                try {
                    System.out.println("Entre com o numero do dia de inicio da oferta: ");
                    mes = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Favor entrar com um double.\n");
                }
                sc.nextLine();
            } while (mes == 0);
        }
        String testemes = mes + "";
        if(testemes.length() < 2){
            testemes = "0"+testemes;
        }

        int dia = 0;
        do {
            try {
                System.out.println("Entre com o dia do inicio da oferta: ");
                dia = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Favor entrar com um double.\n");
            }
            sc.nextLine();
        } while (dia == 0);
        if(dia < 0 && dia > 31){
            System.out.println("Favor entrar com um numero de dia correto.");
            do {
                try {
                    System.out.println("Entre com o numero do dia de inicio da oferta: ");
                    dia = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Favor entrar com um double.\n");
                }
                sc.nextLine();
            } while (dia == 0);
        }
        String testedia = dia + "";
        if(testedia.length() < 2){
            testedia = "0"+testedia;
        }
        String dataFinal = ano+"-"+testemes+"-"+testedia;
        //Date data = null;
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //try {
        //    data = sdf.parse(dataFinal);
        //} catch (ParseException e) {
        //    System.out.println("Erro de conversao de dados");
        //}

        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setDesconto(desconto);
        produto.setPreco(preco);
        produto.setDataInicio(dataFinal);
    }
}
