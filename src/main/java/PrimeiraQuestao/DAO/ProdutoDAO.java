package PrimeiraQuestao.DAO;

import PrimeiraQuestao.modelo.Produto;

import java.sql.*;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean validacao(int id) throws SQLException {
        String sql = "SELECT id FROM ofertas WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                return true; //id existe
            } else {
                return false;
            }
        }
    }

    // TODO: 15/06/2022
    private void incluir(Produto produto) {

    }

    public void atualizar(Produto produto) throws SQLException {
        boolean validar = validacao(produto.getId());
        if(validar == false){
            incluir(produto);
        }

        String sql = "UPDATE ofertas SET nome = ?, descricao = ?, desconto = ?, preco = ?, dataInicio = ? WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setDouble(3, produto.getDesconto());
            pstm.setDouble(4, produto.getPreco());
            pstm.setDate(5, (Date) produto.getDataInicio());
            pstm.setInt(6, produto.getId());

            pstm.execute();

            System.out.println("Produto atualizado com sucesso.");
            try(ResultSet rst = pstm.getGeneratedKeys()){

                produto.setId(rst.getInt(1));
                produto.setNome(rst.getString(2));
                produto.setDescricao(rst.getString(3));
                produto.setDesconto(rst.getDouble(4));
                produto.setPreco(rst.getDouble(5));
                produto.setDataInicio(rst.getDate(6));
                produto.toString();

            }
        }

    }

    public void excluir(int id) throws SQLException {
        boolean validar = validacao(id);
        if(validar == false){
            System.out.println("Oferta nao existente");
        }

        String sql = "DELETE FROM ofertas WHERE id = ?";

        try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstm.setInt(1, id);

            pstm.execute();

            int linhasModificadas = pstm.getUpdateCount();
            System.out.println("O numero de linhas modificadas foi "+ linhasModificadas);
        }
    }

}
