package app;

import controller.CampeonatoController;
import controller.CarreraController;
import model.Campeonato;
import model.Carrera;
import model.Coche;
import model.ResultadoCarrera;
import model.ResultadoParticipante;
import util.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("PROGRAMACION - Simulador de carreras");
        System.out.println("-----------------------------------");

        int numCoches = Input.readInt(sc, "Numero de coches participantes: ", 1, 50);
        int numCarreras = Input.readInt(sc, "Numero de carreras del campeonato: ", 1, 30);

        List<Coche> participantes = new ArrayList<>();
        for (int i = 1; i <= numCoches; i++) {
            System.out.println("\n--- Datos del coche/piloto " + i + " ---");
            String piloto = Input.readNonEmptyString(sc, "Nombre del piloto: ");
            String marca = Input.readNonEmptyString(sc, "Marca del coche: ");
            String modelo = Input.readNonEmptyString(sc, "Modelo del coche: ");

            int velMedia = 120 + random.nextInt(101);
            participantes.add(new Coche(marca, modelo, piloto, i, velMedia));
        }

        List<Carrera> carreras = new ArrayList<>();
        for (int i = 1; i <= numCarreras; i++) {
            int kmObjetivo = Input.readInt(sc, "KM objetivo para la carrera " + i + ": ", 50, 10_000);
            carreras.add(new Carrera(i, kmObjetivo, participantes));
        }

        Campeonato campeonato = new Campeonato(participantes, carreras);

        CarreraController carreraController = new CarreraController();
        CampeonatoController campeonatoController = new CampeonatoController(carreraController);

        for (Carrera carrera : campeonato.getCarreras()) {
            ResultadoCarrera rc = carreraController.simularCarrera(carrera);

            System.out.println("\n=== CARRERA " + rc.getCarrera().getNumero() + " ("
                    + rc.getCarrera().getDistanciaKm() + " km) ===");

            imprimirPodio(rc);
            System.out.println();
            imprimirResultadosCarrera(rc);

            System.out.println("\n=== CLASIFICACION GENERAL (PROVISIONAL) ===");
            System.out.print(campeonatoController.tablaClasificacion(campeonato.getParticipantes()));
            Coche lider = campeonatoController.obtenerLider(campeonato.getParticipantes());
            System.out.println("Lider: " + lider.getPiloto() + " (" + lider.getPuntosTotales() + " puntos)\n");
        }

        System.out.println("=== CLASIFICACION FINAL ===");
        System.out.print(campeonatoController.tablaClasificacion(campeonato.getParticipantes()));
        Coche campeon = campeonatoController.obtenerLider(campeonato.getParticipantes());
        System.out.println("\nCampeon del campeonato: " + campeon.getPiloto()
                + " (dorsal " + campeon.getDorsal() + ") con " + campeon.getPuntosTotales() + " puntos.");
    }

    private static void imprimirPodio(ResultadoCarrera rc) {
        System.out.println("Podio:");
        List<ResultadoParticipante> lista = rc.getResultadosOrdenados();
        for (int i = 0; i < lista.size() && i < 3; i++) {
            ResultadoParticipante r = lista.get(i);
            System.out.println(r.getPosicion() + ") - " + r.getCoche().getPiloto()
                    + " [" + r.getPuntosEnCarrera() + " puntos]");
        }
    }

    private static void imprimirResultadosCarrera(ResultadoCarrera rc) {
        List<ResultadoParticipante> lista = rc.getResultadosOrdenados();

        System.out.printf("%-4s %-20s %-6s %-14s %-10s %-8s%n",
                "Pos", "Piloto", "Dorsal", "Coche", "Tiempo", "Puntos");

        for (ResultadoParticipante r : lista) {
            Coche c = r.getCoche();
            String cocheStr = recortar(c.nombreCoche(), 14);
            String tiempoStr = formatSegundos(c.getTiempoTotalSegundos());

            System.out.printf("%-4s %-20s %-6d %-14s %-10s %-8d%n",
                    r.getPosicion() + ")",
                    recortar(c.getPiloto(), 20),
                    c.getDorsal(),
                    cocheStr,
                    tiempoStr,
                    r.getPuntosEnCarrera());
        }
    }

    private static String formatSegundos(long totalSegundos) {
        long min = totalSegundos / 60;
        long sec = totalSegundos % 60;
        return String.format("%02d:%02d", min, sec);
    }

    private static String recortar(String s, int max) {
        if (s == null) return "";
        return s.length() <= max ? s : s.substring(0, max - 3) + "...";
    }
}

