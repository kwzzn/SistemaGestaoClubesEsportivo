package org.example.Controller;

import org.example.Database.ConexaoBanco;
import org.example.Entity.Treinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadorController {

    public static String postTreinador(Treinador treinador) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_treinador (nome, experiencia) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, treinador.getNome());
            ps.setInt(2, treinador.getExperiencia());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                treinador.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Treinador cadastrado com sucesso!";
    }

    public static Treinador getTreinadorById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_treinador WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return new Treinador(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Treinador de id (" + id + ") não encontrado!");
    }

    public static List<Treinador>  getAllTreinador() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_treinador");
            ResultSet rs = ps.executeQuery();
            List<Treinador> treinadores = new ArrayList<>();
            while (rs.next()) {
                treinadores.add(new Treinador(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                ));
            }
            return treinadores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String deleteTreinadorById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_treinador WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Treinador deletado com sucesso!";
    }

    public static String editTreinador(Treinador treinador) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            getTreinadorById(treinador.getId()); // verifica se o treinador existe.
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_treinador SET nome = ?, experiencia = ? WHERE id = ?");
            ps.setString(1, treinador.getNome());
            ps.setInt(2, treinador.getExperiencia());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Treinador editado com sucesso!";
    }
}
