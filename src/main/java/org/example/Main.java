package org.example;

import org.example.Controller.*;
import org.example.Entity.*;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static boolean system = true;

    public static void main(String[] args) {


        do {

            int escolhaMenuPrincipal = 0;

            System.out.print("\n==== SISTEMA DE GESTÃO DE CLUBES ESPORTIVOS ====\n" +
                    "1. Gerenciar Clubes\n" +
                    "2. Gerenciar Treinadores\n" +
                    "3. Gerenciar Jogadores\n" +
                    "4. Gerenciar Ligas\n" +
                    "5. Sair\n" +
                    "Escolha uma opção:");
            escolhaMenuPrincipal = sc.nextInt();
            sc.nextLine();

            switch (escolhaMenuPrincipal) {
                case 1:
                    menuGerenciarClube();
                    break;
                case 2:
                    menuGerenciarTreinadores();
                    break;
                case 3:
                    menuGerenciarJogadores();
                    break;
                case 4:
                    menuGerenciarLigas();
                    break;
                case 5:
                    system = false;
                    break;
            }

        } while (system != false);


    }

    private static void menuGerenciarClube() {

        System.out.print("\n==== GERENCIAR CLUBES ====\n" +
                "1. Cadastrar novo clube\n" +
                "2. Listar todos os clubes\n" +
                "3. Atualizar dados de um clube\n" +
                "4. Excluir um clube\n" +
                "5. Gerenciar ligas do clube\n" +
                "6. Voltar ao menu principal\n" +
                "Escolha uma opção:");
        int escolhaMenu = sc.nextInt();
        sc.nextLine();

        if (escolhaMenu == 1) {

            System.out.print("\nPreencha os dados para cadastrar novo clube! \n\n Nome: ");
            String nome = sc.nextLine();

            System.out.print("\nID do treinador: ");
            int idTreinador = sc.nextInt();
            sc.nextLine();

            System.out.print("\nInforme o ano de fundação: ");
            int anoFundacao = sc.nextInt();
            sc.nextLine();

            Clube clube = new Clube(nome, idTreinador, anoFundacao);
            ClubeController.postClube(clube);

        } else if (escolhaMenu == 2) {
            System.out.println(ClubeController.getAllClubes());

        } else if (escolhaMenu == 3) {

            System.out.print("\nInforme o ID do Clube que deseja editar: ");
            int clubeId = sc.nextInt();
            sc.nextLine();

            System.out.print("\nInforme o nome do Clube: ");
            String nomeClube = sc.nextLine();

        } else if (escolhaMenu == 4) {

            System.out.print("\nInforme o ID do Clube que deseja deletar: ");
            int clubeId = sc.nextInt();
            sc.nextLine();

            ClubeController.deleteClubeById(clubeId);

        } else if (escolhaMenu == 5) {
            System.out.print("\n==== GERENCIAR LIGAS DO CLUBE ====\n" +
                    "1. Listar ligas associadas ao clube\n" +
                    "2. Adicionar clube a uma liga\n" +
                    "3. Remover clube de uma liga\n" +
                    "4. Voltar ao menu anterior\n" +
                    "Escolha uma opção:");
            int escolhaGerenciarLigas = sc.nextInt();
            sc.nextLine();

            if (escolhaGerenciarLigas == 1) {

                System.out.print("\nInforme o ID do clube que deseja buscar as ligas associadas: ");
                int clubeId = sc.nextInt();
                sc.nextLine();

                System.out.println(LigaComClubeController.listLigasAssociadasAoClube(clubeId));
            }

            else if (escolhaGerenciarLigas == 2) {
                System.out.println("\nInforme o ID do clube que deseja adicionar: ");
                int clubeId = sc.nextInt();
                sc.nextLine();

                System.out.print("\nInforme o ID da liga que deseja adicionar ao clube " + ClubeController.getClubeById(clubeId).getNome() + ": ");
                int ligaId = sc.nextInt();
                sc.nextLine();

                LigaComClube ligaComClube = new LigaComClube(clubeId, ligaId);
                LigaComClubeController.postLigaComClube(ligaComClube);
            }

            else if (escolhaGerenciarLigas == 3) {
                System.out.print("\nInforme o ID da relação entra clube e liga que deseja remover: ");
                int id = sc.nextInt();
                sc.nextLine();

                LigaComClubeController.deleteLigaComClubesById(id);
            }

            else if (escolhaGerenciarLigas == 4) {
                System.out.println("Voltando...");
            } else {
                System.out.println("Erro...");
            }

        } else if (escolhaMenu == 6) {
            System.out.println("Voltando...");
        }
    }

    private static void menuGerenciarTreinadores() {

        System.out.println("\n==== GERENCIAR TREINADORES ====\n" +
                "1. Cadastrar novo treinador\n" +
                "2. Listar todos os treinadores\n" +
                "3. Atualizar dados de um treinador\n" +
                "4. Excluir um treinador\n" +
                "5. Voltar ao menu principal\n" +
                "Escolha uma opção:");
        int escolhaMenu = sc.nextInt();
        sc.nextLine();

        if (escolhaMenu == 1) {

            System.out.print("\nInforme o nome do treinador: ");
            String nome = sc.nextLine();

            System.out.print("\nInforme o digito da experiencia do treinador " + nome + ": ");
            int experiencia = sc.nextInt();
            sc.nextLine();

            Treinador treinador = new Treinador(nome, experiencia);
            TreinadorController.postTreinador(treinador);
        }

        else if (escolhaMenu == 2) {
            System.out.println(TreinadorController.getAllTreinador());
        }

        else if (escolhaMenu == 3) {
            System.out.print("\nInforme o id do treinador que deseja editar: ");
            int id = sc.nextInt();

            System.out.print("\nInforme o novo nome de " + TreinadorController.getTreinadorById(id).getNome() + ": ");
            String nome = sc.nextLine();

            System.out.print("\nInforme a nova experiencia do treinador " + nome + ", (experiencia atual: " + TreinadorController.getTreinadorById(id).getExperiencia() + "): ");
            int experiencia = sc.nextInt();

            Treinador treinador = new Treinador(id, nome, experiencia);
            TreinadorController.editTreinador(treinador);
        }

        else if (escolhaMenu == 4) {
            System.out.print("\nInforme o ID do treinador que deseja excluir: ");
            int id = sc.nextInt();
            sc.nextLine();

            TreinadorController.deleteTreinadorById(id);
        }

        else if (escolhaMenu == 5) {
            System.out.println("Voltando...");
        }
    }

    private static void menuGerenciarJogadores() {

        System.out.println("\n==== GERENCIAR JOGADORES ====\n" +
                "1. Cadastrar novo jogador\n" +
                "2. Listar todos os jogadores\n" +
                "3. Listar jogadores de um clube\n" +
                "4. Atualizar dados de um jogador\n" +
                "5. Excluir um jogador\n" +
                "6. Voltar ao menu principal\n" +
                "Escolha uma opção:");
        int escolhaMenu = sc.nextInt();
        sc.nextLine();

        if (escolhaMenu == 1) {
            System.out.print("\nInforme o nome do jogador que deseja cadastrar: ");
            String nome = sc.nextLine();

            System.out.print("\nInforme a idade do " + nome + ": ");
            int idade = sc.nextInt();
            sc.nextLine();

            System.out.print("\nInforme a posição do " + nome + ": ");
            String posicao = sc.nextLine();

            System.out.println("\nInforme o ID do clube que deseja adicionar ao " + nome + ": ");
            int clubeId = sc.nextInt();
            sc.nextLine();

            Jogador jogador = new Jogador(nome, idade, posicao, clubeId);
            JogadorController.postJogador(jogador);
        }

        else if (escolhaMenu == 2) {
            System.out.println(JogadorController.getAllJogadores());
        }

        else if (escolhaMenu == 3) {
            System.out.print("\nInforme o ID do clube que deseja buscar os jogadores: ");
            int idClube = sc.nextInt();
            sc.nextLine();

            System.out.println(JogadorController.getAllJogadoresbyIdClube(idClube));
        }

        else if (escolhaMenu == 4) {
            System.out.print("\nInforme o ID do jogador que deseja editar: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("\nInforme o novo nome do " + JogadorController.getJogadorById(id).getNome() + ": ");
            String nome = sc.nextLine();

            System.out.print("\nInforme a nova idade do " + nome + " (idade atual: " + JogadorController.getJogadorById(id).getIdade() + "): " );
            int idade = sc.nextInt();
            sc.nextLine();

            System.out.print("\nInforme a posicao do " + nome + ", (posição atual: " + JogadorController.getJogadorById(id).getPosicao() + "): " );
            String posicao = sc.nextLine();

            System.out.print("\nInforme o novo id do clube (atual: " + JogadorController.getJogadorById(id).getIdClube() + "): ");
            int idClube = sc.nextInt();
            sc.nextLine();

            Jogador jogador = new Jogador(id, nome, idade, posicao, idClube);
            JogadorController.editJogador(jogador);
        }

        else if (escolhaMenu == 5) {
            System.out.print("\nnforme o ID do jogador que deseja excluir: ");
            int id = sc.nextInt();
            sc.nextLine();

            JogadorController.deleteJogadorById(id);
        }

        else if (escolhaMenu == 6) {
            System.out.println("Voltando...");
        } else {
            System.out.println("Ops, ocorreu algum erro, tente novamente...");
        }

    }

    private static void menuGerenciarLigas() {

        System.out.print("\n==== GERENCIAR LIGAS ====\n" +
                "1. Cadastrar nova liga\n" +
                "2. Listar todas as ligas\n" +
                "3. Atualizar dados de uma liga\n" +
                "4. Excluir uma liga\n" +
                "5. Listar clubes em uma liga\n" +
                "6. Voltar ao menu principal\n" +
                "Escolha uma opção:");
        int escolhaMenu = sc.nextInt();
        sc.nextLine();

        if (escolhaMenu == 1) {
            System.out.print("\nInforme o nome da liga que deseja cadastrar: ");
            String nome = sc.nextLine();

            System.out.print("\nInforme o ano da fundação da liga: ");
            int anoFundacao = sc.nextInt();
            sc.nextLine();

            Liga liga = new Liga(nome, anoFundacao);
            LigaController.postLiga(liga);
        }

        else if (escolhaMenu == 2) {
            System.out.println(LigaController.getAllLigas());
        }

        else if (escolhaMenu == 3) {

            System.out.print("\nInforme o ID da liga que deseja editar: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("\nInforme o novo nome da liga " + LigaController.getLigaById(id).getNome() + ": ");
            String nome = sc.nextLine();

            System.out.print("\nInforme o novo ano de fundação da liga (atual: " + LigaController.getLigaById(id).getAnoFundacao() + "): ");
            int anoFundacao = sc.nextInt();
            sc.nextLine();

            Liga liga = new Liga(id, nome, anoFundacao);
            LigaController.editLiga(liga);
        }

        else if (escolhaMenu == 4) {
            System.out.print("Informe o ID da liga que deseja apagar: ");
            int id = sc.nextInt();
            sc.nextLine();

            LigaController.deleteLigaById(id);
        }

        else if (escolhaMenu == 5) {

            System.out.print("\nInforme o ID da liga que deseja buscar os clubes: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println(LigaComClubeController.listClubesAssociadosAoLiga(id));
        }

        else if (escolhaMenu == 6) {
            System.out.println("Voltar...");
        }
    }

}