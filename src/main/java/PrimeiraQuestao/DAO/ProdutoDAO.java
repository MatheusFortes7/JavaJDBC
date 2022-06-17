package PrimeiraQuestao.DAO;

import PrimeiraQuestao.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean validacao(int id) throws SQLException {
        String sql = "SELECT id FROM produto WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                return true; //id existe
            } else {
                return false; //id nao existe
            }
        }
    }

    public void incluir(Produto produto) {
        boolean validar = false;
        try {
            validar = validacao(produto.getId());
        } catch (SQLException e) {
            System.out.println("Erro na validação");
        }
        if(validar == false){

            String sqlValid = "SELECT * FROM produto";

            try(PreparedStatement pstm = connection.prepareStatement(sqlValid)){

                pstm.execute();

                try(ResultSet rst = pstm.getResultSet()){
                    if(!rst.next()){
                        incluir3Produtos();
                    }
                }
            } catch (SQLException e) {
                System.out.println("erro na validação da inserção.");
            }

            String sql = "INSERT INTO produto (id, nome, descricao, desconto, preco, dataInicio) VALUES (?, ?, ?, ?, ?, ?)";

            try(PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

                pstm.setInt(1, produto.getId());
                pstm.setString(2, produto.getNome());
                pstm.setString(3, produto.getDescricao());
                pstm.setDouble(4, produto.getDesconto());
                pstm.setDouble(5, produto.getPreco());
                pstm.setString(6, produto.getDataInicio());

                pstm.execute();

                try(ResultSet rst = pstm.getGeneratedKeys()){
                    while (rst.next()){
                        produto.setId(rst.getInt(1));
                    }
                }

                System.out.println("Oferta inserida com sucesso");
                produto.toString();

            } catch (SQLException e) {
                System.out.println("Erro na inclusao no banco de dados");
            }
        } else {
            System.out.println("Id ja existente, nao foi possível a inserção da oferta!");
        }
    }

    private void incluir3Produtos() {
        String sql = "INSERT INTO produto (id, nome, descricao, desconto, preco, dataInicio) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

            pstm.setInt(1, 997);
            pstm.setString(2, "Notebook");
            pstm.setString(3, "Notebook gamer Asus");
            pstm.setDouble(4, 500);
            pstm.setDouble(5, 5500);
            pstm.setString(6, "2022-05-18");

            pstm.execute();


            System.out.println("Oferta default 1 inserida com sucesso");


        } catch (SQLException e) {
            System.out.println("Erro na inclusao no banco de dados");
        }

        try(PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

            pstm.setInt(1, 998);
            pstm.setString(2, "Televisao");
            pstm.setString(3, "Televisao Samsung 50 polegadas");
            pstm.setDouble(4, 300);
            pstm.setDouble(5, 7000);
            pstm.setString(6, "2022-03-29");

            pstm.execute();


            System.out.println("Oferta default 2 inserida com sucesso");


        } catch (SQLException e) {
            System.out.println("Erro na inclusao no banco de dados");
        }
        try(PreparedStatement pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

            pstm.setInt(1, 999);
            pstm.setString(2, "Geladeira");
            pstm.setString(3, "geladeira eletrolux");
            pstm.setDouble(4, 150);
            pstm.setDouble(5, 2000);
            pstm.setString(6, "2020-01-09");

            pstm.execute();


            System.out.println("Oferta default 3 inserida com sucesso");


        } catch (SQLException e) {
            System.out.println("Erro na inclusao no banco de dados");
        }
    }

    public void atualizar(Produto produto) {
        boolean validar = false;
        try {
            validar = validacao(produto.getId());
        } catch (SQLException e) {
            System.out.println("Erro na validação do bd");
        }
        if(validar == false){
            incluir(produto);
        }

        String sql = "UPDATE produto SET nome = ?, descricao = ?, desconto = ?, preco = ?, dataInicio = ? WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setDouble(3, produto.getDesconto());
            pstm.setDouble(4, produto.getPreco());
            pstm.setString(5, produto.getDataInicio());
            pstm.setInt(6, produto.getId());

            pstm.execute();

            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro no UPDATE");
        }

    }

    public void excluir(int id) {
        boolean validar = false;
        try {
            validar = validacao(id);
        } catch (SQLException e) {
            System.out.println("Erro na validação do bd");
        }
        if(validar == false){
            System.out.println("Oferta nao existente! Não e possivel excluir um objeto que não existe.");
        }

        String sql = "DELETE FROM produto WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstm.setInt(1, id);

            pstm.execute();

            int linhasModificadas = pstm.getUpdateCount();
            System.out.println("O numero de linhas modificadas foi "+ linhasModificadas);
        } catch (SQLException e) {
            System.out.println("Erro no DELETE");
        }
    }

    public List<Produto> listar(String nomeProduto) {

        List<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT id, nome, descricao, desconto, preco, dataInicio FROM produto WHERE nome LIKE ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setString(1, nomeProduto);
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()){
                while (rst.next()){
                    Produto produto = new Produto(rst.getInt(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getDouble(4),
                            rst.getDouble(5),
                            rst.getString(6));

                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro no SELECT");
        }
        return produtos;
    }
}
