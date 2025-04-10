package org.example.Controller;

import org.example.Database.ConexaoBanco;
import org.example.Entity.Clube;
import org.example.Entity.Jogador;
import org.example.Entity.Liga;
import org.example.Entity.LigaComClube;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LigaComClubeController {

    public static String postLigaComClube(LigaComClube ligaComClube) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_liga_com_clube (clubeId, ligaId) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ligaComClube.getClubeId());
            ps.setInt(2, ligaComClube.getLigaId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ligaComClube.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Clube registrado na liga com sucesso!";
    }

    public static LigaComClube getLigaComClubeById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga_com_clube WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                return new LigaComClube(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Clube com liga de id (" + id + ") não encontrado!");
    }

    public static List<LigaComClube> getAllLigasComClubes() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga_com_clube");
            ResultSet rs = ps.executeQuery();
            List<LigaComClube> ligaComClubes = new ArrayList<>();
            while (rs.next()) {
                ligaComClubes.add(new LigaComClube(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                ));
            }
            return ligaComClubes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String deleteLigaComClubesById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_liga_com_clube WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ("Liga com clube deletado com sucesso!");
    }

    public static String deleteLigaComClubesByClubeId(int clubeId) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_liga_com_clube WHERE clubeId = ?");
            ps.setInt(1, clubeId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ("Liga com clube deletado com sucesso!");
    }

    public static String editLigaComClube(LigaComClube ligaComClube) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            getLigaComClubeById(ligaComClube.getId()); // verifica se o jogador existe.
            PreparedStatement ps = connection.prepareStatement("UPDATE tb_liga_com_clube SET clubeId = ?, ligaId = ? WHERE ID = ?");
            ps.setInt(1, ligaComClube.getClubeId());
            ps.setInt(2, ligaComClube.getLigaId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Liga com clube editado com sucesso!";
    }

    public static List<Liga> listLigasAssociadasAoClube(int clubeId) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga_com_clube WHERE clubeId = ?");
            ResultSet rs = ps.executeQuery();
            List<Liga> ligas = new ArrayList<>();

            while (rs.next()) {
                ligas.add(LigaController.getLigaById(rs.getInt(3)));
            }
            return ligas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Clube> listClubesAssociadosAoLiga(int ligaId) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_liga_com_clube WHERE ligaId = ?");
            ResultSet rs = ps.executeQuery();
            List<Clube> clubes = new ArrayList<>();

            while (rs.next()) {
                clubes.add(ClubeController.getClubeById(rs.getInt(3)));
            }
            return clubes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
