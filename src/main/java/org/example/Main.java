package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static DivisionDao divisionDao = new DivisionDao();
    private static MatchDao matchDao = new MatchDao();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            mostrarMenu();
        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL - SISTEMA FÚTBOL ===");
            System.out.println("1. Partidos en ligas de España - Temporada 2007");
            System.out.println("2. Máximo de goles del equipo visitante");
            System.out.println("3. Equipos locales con apariciones por temporada");
            System.out.println("4. Partidos con ≥5 goles (local o visitante)");
            System.out.println("5. Partidos Tenerife vs Zaragoza + Estadísticas");
            System.out.println("6. Mostrar todas las divisiones");
            System.out.println("7. Mostrar todos los partidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    consultaPartidosEspana2007();
                    break;
                case 2:
                    consultaMaxGolesVisitante();
                    break;
                case 3:
                    consultaEquiposLocalesTemporada();
                    break;
                case 4:
                    consultaPartidosConMinGoles();
                    break;
                case 5:
                    consultaPartidosTenerifeZaragoza();
                    break;
                case 6:
                    mostrarTodasDivisiones();
                    break;
                case 7:
                    mostrarTodosPartidos();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            if (opcion != 0) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 0);
    }

    private static void consultaPartidosEspana2007() {
        System.out.println("\n--- PARTIDOS - TEMPORADA 2007 ---");
        List<Match> partidos = matchDao.findMatchesInSpain2007();

        if (partidos.isEmpty()) {
            System.out.println("No se encontraron partidos para la temporada 2007.");
        } else {
            System.out.println("Partidos de la temporada 2007:");
            partidos.forEach(partido -> {
                System.out.println(partido.getSeason() + " - " +
                        partido.getLocalTeamEffective() + " " +
                        partido.getLocalGoalsEffective() + " - " +
                        partido.getVisitorGoalsEffective() + " " +
                        partido.getVisitorTeamEffective());
            });
            System.out.println("Total partidos 2007: " + partidos.size());
        }
    }

    private static void consultaMaxGolesVisitante() {
        System.out.println("\n--- MÁXIMO DE GOLES DEL EQUIPO VISITANTE ---");
        Integer maxGoles = matchDao.findMaxVisitorGoals();
        System.out.println("El máximo número de goles marcados por un equipo visitante es: " + maxGoles);
    }

    private static void consultaEquiposLocalesTemporada() {
        System.out.println("\n--- EQUIPOS LOCALES CON APARICIONES POR TEMPORADA ---");
        List<Object[]> resultados = matchDao.findLocalTeamAppearancesBySeason();

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron datos.");
        } else {
            System.out.println("Equipo | Temporada | Partidos jugados");
            System.out.println("-------------------------------------");
            for (Object[] resultado : resultados) {
                System.out.println(resultado[0] + " | " + resultado[1] + " | " + resultado[2]);
            }
            System.out.println("Total registros: " + resultados.size());
        }
    }

    private static void consultaPartidosConMinGoles() {
        System.out.println("\n--- PARTIDOS CON ≥5 GOLES (LOCAL O VISITANTE) ---");
        List<Match> partidos = matchDao.findMatchesWithMinGoals();

        if (partidos.isEmpty()) {
            System.out.println("No se encontraron partidos que cumplan el criterio.");
        } else {
            partidos.forEach(partido -> {
                System.out.println(partido.getLocalTeamEffective() + " " +
                        partido.getLocalGoalsEffective() + " - " +
                        partido.getVisitorGoalsEffective() + " " +
                        partido.getVisitorTeamEffective() + " (" +
                        partido.getSeason() + ")");
            });
            System.out.println("Total partidos con ≥5 goles: " + partidos.size());
        }
    }

    private static void consultaPartidosTenerifeZaragoza() {
        System.out.println("\n--- PARTIDOS TENERIFE vs ZARAGOZA ---");
        List<Match> partidos = matchDao.findMatchesBetweenTenerifeZaragoza();

        if (partidos.isEmpty()) {
            System.out.println("No se encontraron partidos entre Tenerife y Zaragoza.");
        } else {
            // Mostrar partidos
            System.out.println("Partidos encontrados:");
            partidos.forEach(partido -> {
                System.out.println(partido.getSeason() + ": " +
                        partido.getLocalTeamEffective() + " " +
                        partido.getLocalGoalsEffective() + " - " +
                        partido.getVisitorGoalsEffective() + " " +
                        partido.getVisitorTeamEffective());
            });

            // Calcular estadísticas
            calcularEstadisticasTenerifeZaragoza(partidos);
        }
    }

    private static void calcularEstadisticasTenerifeZaragoza(List<Match> partidos) {
        int victoriasTenerife = 0;
        int victoriasZaragoza = 0;
        int empates = 0;

        for (Match partido : partidos) {
            String ganador = partido.getWinner();
            if ("Tenerife".equals(ganador)) {
                victoriasTenerife++;
            } else if ("Zaragoza".equals(ganador)) {
                victoriasZaragoza++;
            } else if ("Empate".equals(ganador)) {
                empates++;
            }
        }

        System.out.println("\n--- ESTADÍSTICAS TENERIFE vs ZARAGOZA ---");
        System.out.println("Total partidos: " + partidos.size());
        System.out.println("Victorias Tenerife: " + victoriasTenerife);
        System.out.println("Victorias Zaragoza: " + victoriasZaragoza);
        System.out.println("Empates: " + empates);
    }

    private static void mostrarTodasDivisiones() {
        System.out.println("\n--- TODAS LAS DIVISIONES ---");
        List<Division> divisiones = divisionDao.findAll();

        if (divisiones.isEmpty()) {
            System.out.println("No hay divisiones en la base de datos.");
        } else {
            divisiones.forEach(System.out::println);
            System.out.println("Total: " + divisiones.size() + " divisiones");
        }
    }

    private static void mostrarTodosPartidos() {
        System.out.println("\n--- TODOS LOS PARTIDOS (primeros 20) ---");
        List<Match> partidos = matchDao.findAll();

        if (partidos.isEmpty()) {
            System.out.println("No hay partidos en la base de datos.");
        } else {
            // Mostrar solo los primeros 20 para no saturar
            int limite = Math.min(partidos.size(), 20);
            for (int i = 0; i < limite; i++) {
                System.out.println(partidos.get(i));
            }
            if (partidos.size() > 20) {
                System.out.println("... y " + (partidos.size() - 20) + " partidos más");
            }
            System.out.println("Total: " + partidos.size() + " partidos");
        }
    }
}