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

    public void incluir(Produto produto) throws SQLException {
        boolean validar = validacao(produto.getId());
        if(validar == false){
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

    public void atualizar(Produto produto) throws SQLException {
        boolean validar = validacao(produto.getId());
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
        }

    }

    public void excluir(int id) throws SQLException {
        boolean validar = validacao(id);
        if(validar == false){
            System.out.println("Oferta nao existente! Não e possivel excluir um objeto que não existe.");
        }

        String sql = "DELETE FROM produto WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstm.setInt(1, id);

            pstm.execute();

            int linhasModificadas = pstm.getUpdateCount();
            System.out.println("O numero de linhas modificadas foi "+ linhasModificadas);
        }
    }

    public List<Produto> listar(String nomeProduto) throws SQLException {

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
        }
        return produtos;
    }
}
