package org.example.Controller;

import org.example.Database.ConexaoBanco;
import org.example.Entity.Jogador;
import org.example.Entity.Liga;
import org.example.Entity.Treinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorController {

    public static String postJogador(Jogador jogador) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_jogador (nome, idade, posicao, idClube) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, jogador.getNome());
            ps.setInt(2, jogador.getIdade());
            ps.setString(3, jogador.getPosicao());
            ps.setInt(4, jogador.getIdClube());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                jogador.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Jogador cadastrado com sucesso!";
    }

    public static Jogador getJogadorById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogador WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return new Jogador(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Jogador de id (" + id + ") não encontrado!");
    }


    public static List<Jogador> getAllJogadores() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogador");
            ResultSet rs = ps.executeQuery();
            List<Jogador> jogadores = new ArrayList<>();
            while (rs.next()) {
                jogadores.add(new Jogador(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5)
                ));
            }
            return jogadores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Jogador> getAllJogadoresbyIdClube (int clubeId) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogador WHERE idClube = ?");
            ResultSet rs = ps.executeQuery();
            List<Jogador> jogadores = new ArrayList<>();
            while (rs.next()) {
                jogadores.add(new Jogador(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5)
                ));
            }
            return jogadores;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String deleteJogadorById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_jogador WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ("Jogador deletado com sucesso!");
    }

    public static String editJogador(Jogador jogador) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            getJogadorById(jogador.getId()); // verifica se o jogador existe.
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_jogador SET nome = ?, idade = ?, posicao = ?, idClube = ? WHERE id = ?");
            ps.setString(1, jogador.getNome());
            ps.setInt(2, jogador.getIdade());
            ps.setString(3, jogador.getPosicao());
            ps.setInt(4, jogador.getIdClube());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Jogador editado com sucesso!";
    }



}
