package org.example.Controller;

import org.example.Database.ConexaoBanco;
import org.example.Entity.Clube;
import org.example.Entity.Liga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LigaController {

    public static String postLiga(Liga liga) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_liga (nome, anoFundacao) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, liga.getNome());
            ps.setInt(2, liga.getAnoFundacao());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                liga.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Liga cadastrada com sucesso!";
    }

    public static Liga getLigaById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return new Liga(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Liga id (" + id + ") não encontrado!");
    }

    public static List<Liga> getAllLigas() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga");
            ResultSet rs = ps.executeQuery();
            List<Liga> ligas = new ArrayList<>();
            while (rs.next()) {
                ligas.add(new Liga(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));
            }
            return ligas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String deleteLigaById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_liga WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ("Liga deletada com sucesso!");
    }

    public static String editLiga(Liga liga) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            getLigaById(liga.getId()); // verifica se o jogador existe.
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_liga SET nome = ?, anoFundacao = ? WHERE id = ?");
            ps.setString(1, liga.getNome());
            ps.setInt(2, liga.getAnoFundacao());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Clubeeditado com sucesso!";
    }
}
