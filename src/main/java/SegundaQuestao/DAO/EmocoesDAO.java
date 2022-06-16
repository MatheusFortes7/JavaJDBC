package SegundaQuestao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmocoesDAO {

    private Connection connection;

    public EmocoesDAO(Connection connection) {
        this.connection = connection;
    }

    public void incluir(String sentimento){
        String sql = "INSERT INTO info (sentimento) VALUE ( ? )";

        try {
            PreparedStatement pstm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, sentimento);

            pstm.execute();

            System.out.println("\nInformação adicionada no banco de dados...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }


}
