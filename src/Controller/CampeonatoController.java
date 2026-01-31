package controller;

import model.Campeonato;
import model.Carrera;
import model.Coche;
import model.ResultadoCarrera;

import java.util.ArrayList;
import java.util.List;

public class CampeonatoController {

    private final CarreraController carreraController;

    public CampeonatoController(CarreraController carreraController) {
        this.carreraController = carreraController;
    }

    public List<ResultadoCarrera> ejecutarCampeonato(Campeonato campeonato) {
        List<ResultadoCarrera> resultados = new ArrayList<>();

        for (Carrera carrera : campeonato.getCarreras()) {
            ResultadoCarrera res = carreraController.simularCarrera(carrera);
            resultados.add(res);
        }

        return resultados;
    }

    public List<Coche> obtenerClasificacionGeneral(List<Coche> participantes) {
        List<Coche> ordenados = new ArrayList<>(participantes);
        ordenarClasificacionGeneral(ordenados);
        return ordenados;
    }

    public Coche obtenerLider(List<Coche> participantes) {
        List<Coche> clas = obtenerClasificacionGeneral(participantes);
        return clas.get(0);
    }

    public String tablaClasificacion(List<Coche> participantes) {
        List<Coche> ordenados = obtenerClasificacionGeneral(participantes);

        StringBuilder sb = new StringBuilder();
        sb.append("Pos\tPiloto\t\tCoche\t\tDorsal\tPuntos\n");
        sb.append("------------------------------------------------\n");

        for (int i = 0; i < ordenados.size(); i++) {
            Coche c = ordenados.get(i);
            sb.append((i + 1) + ")\t");
            sb.append(recortar(c.getPiloto(), 16));
            sb.append("\t");
            String coche = c.getMarca() + " " + c.getModelo();
            sb.append(recortar(coche, 18));
            sb.append("\t");
            sb.append(c.getDorsal());
            sb.append("\t");
            sb.append(c.getPuntosTotales());
            sb.append("\n");
        }

        return sb.toString();
    }

    private void ordenarClasificacionGeneral(List<Coche> coches) {
        int n = coches.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Coche a = coches.get(j);
                Coche b = coches.get(j + 1);

                if (debeIntercambiarGeneral(a, b)) {
                    coches.set(j, b);
                    coches.set(j + 1, a);
                }
            }
        }
    }

    private boolean debeIntercambiarGeneral(Coche a, Coche b) {
        if (a.getPuntosTotales() < b.getPuntosTotales()) return true;
        if (a.getPuntosTotales() > b.getPuntosTotales()) return false;

        int cmp = a.getPiloto().compareToIgnoreCase(b.getPiloto());
        if (cmp > 0) return true;
        if (cmp < 0) return false;

        return a.getDorsal() > b.getDorsal();
    }

    private String recortar(String s, int maxLen) {
        if (s == null) return "";
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 3) + "...";
    }
}

