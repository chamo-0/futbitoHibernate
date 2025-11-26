package org.example;

import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static DivisionDao divisionDao = new DivisionDao();
    private static MatchDao matchDao = new MatchDao();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            mostrarMenu(session);
        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static void mostrarMenu(Session session) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL - SISTEMA FÚTBOL ===");
            System.out.println("1. Partidos en ligas de España - Temporada 2007");
            System.out.println("2. Máximo de goles del equipo visitante");
            System.out.println("3. Equipos locales con apariciones por temporada");
            System.out.println("4. Partidos con ≥5 goles (local o visitante)");
            System.out.println("5. Partidos Tenerife vs Zaragoza + Estadísticas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");


            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

                switch (opcion){
                    case 1:
                        List<Match> lista = matchDao.encontrarPartidosEspana2007(session);
                        for (Match m : lista) System.out.println(m);
                        break;
                    case 2:
                        List<Match> listaGolesMax = matchDao.MaxGoles(session);
                        for (Match m : listaGolesMax) System.out.println(m);
                        break;
                    case 3:
                        List<Object[]> listaEquipos = matchDao.DevolverEquipos(session);
                        for (Object[] fila : listaEquipos) {
                            String equipo = (String) fila[0];
                            int temporada = (int) fila[1];
                            long apariciones = (long) fila[2];
                            System.out.println("Equipo: " + equipo + ", Temporada: " + temporada + ", Apariciones: " + apariciones);
                        }
                        break;
                    case 4:
                        List<Match> listaDivisiones = matchDao.divisionesConAlMenos5Goles(session);
                        for (Match d : listaDivisiones) System.out.println(d);
                        break;
                    case 5:
                        List<Match> listaPartidos = matchDao.partidosTenerifeZaragoza(session);
                        int winsTenerife = 0;
                        int winsZaragoza = 0;
                        int draws = 0;

                        for (Match m : listaPartidos) {
                            int homeGoals = (int) m.getFthg();
                            int awayGoals = (int) m.getFtag();
                            if (homeGoals > awayGoals) {
                                if ("Tenerife".equals(m.getHomeTeam())) winsTenerife++;
                                else winsZaragoza++;
                            } else if (awayGoals > homeGoals) {
                                if ("Tenerife".equals(m.getAwayTeam())) winsTenerife++;
                                else winsZaragoza++;
                            } else {
                                draws++;
                            }
                        }
                        System.out.println("Partidos totales: " + listaPartidos.size());
                        System.out.println("Tenerife ganó: " + winsTenerife);
                        System.out.println("Zaragoza ganó: " + winsZaragoza);
                        System.out.println("Empates: " + draws);
                }
                if (opcion != 0) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 0);
    }
}

