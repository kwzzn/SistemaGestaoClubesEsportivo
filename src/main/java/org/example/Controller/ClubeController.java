package org.example.Controller;

import org.example.Database.ConexaoBanco;
import org.example.Entity.Clube;
import org.example.Entity.Jogador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubeController {

    public static String postClube(Clube clube) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_clube (nome, treinadorId, dataFundacao) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clube.getNome());
            ps.setInt(2, clube.getTreinadorId());
            ps.setInt(3, clube.getDataFundacao());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                clube.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Clube cadastrado com sucesso!";
    }

    public static Clube getClubeById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clube WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return new Clube(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Clube de id (" + id + ") não encontrado!");
    }

    public static List<Clube> getAllClubes() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clube");
            ResultSet rs = ps.executeQuery();
            List<Clube> clubes = new ArrayList<>();
            while (rs.next()) {
                clubes.add(new Clube(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                ));
            }
            return clubes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String deleteClubeById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_clube WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            LigaComClubeController.deleteLigaComClubesByClubeId(id);
            deleteClubeById(id);

            /*
            * Esse método está relacionado a deletar em cascata, onde ele manda um comando SQL para deletar
            * todas as relações que usam essa chave estrangeira. O tipo do catch sempre vai ser o mesmo.
            * Também é importante lembrar de sempre chamar o mesmo metodo para fazer o processo novamente, como
            * as relações ja foram deletadas, ele vai conseguir executar o método novamente e dar boa.
            * */

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ("Clube deletado com sucesso!");
    }

    public static String editClube(Clube clube) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            getClubeById(clube.getId()); // verifica se o jogador existe.
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_clube SET nome = ?, treinadorId = ?, dataFundacao = ? WHERE id = ?");
            ps.setString(1, clube.getNome());
            ps.setInt(2, clube.getTreinadorId());
            ps.setInt(3, clube.getDataFundacao());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Clubeeditado com sucesso!";
    }
}
