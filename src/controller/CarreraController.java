package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarreraController {

    private final Random random;

    public CarreraController() {
        this(new Random());
    }

    public CarreraController(Random random) {
        this.random = random;
    }

    public model.ResultadoCarrera simularCarrera(model.Carrera carrera) {
        for (model.Coche coche : carrera.getParticipantes()) {
            coche.resetearParaNuevaCarrera();
        }

        int objetivoKm = carrera.getDistanciaKm();

        boolean hayGanador = false;
        while (!hayGanador) {
            for (model.Coche coche : carrera.getParticipantes()) {
                int kmTramo = random.nextInt(31) + 20; // 20..50
                coche.sumarKm(kmTramo);
                coche.sumarTiempoPorTramo(kmTramo);
            }

            for (model.Coche coche : carrera.getParticipantes()) {
                if (coche.getKmRecorridos() >= objetivoKm) {
                    hayGanador = true;
                    break;
                }
            }
        }

        List<model.Coche> ordenados = new ArrayList<>(carrera.getParticipantes());
        ordenarResultadosCarrera(ordenados);

        List<model.ResultadoParticipante> resultados = new ArrayList<>();
        for (int i = 0; i < ordenados.size(); i++) {
            int posicion = i + 1;
            int puntos = puntosPorPosicion(posicion);

            ordenados.get(i).sumarPuntos(puntos);

            resultados.add(new model.ResultadoParticipante(ordenados.get(i), posicion, puntos));
        }

        return new model.ResultadoCarrera(carrera, resultados);
    }

    private int puntosPorPosicion(int posicion) {
        if (posicion == 1) return 10;
        if (posicion == 2) return 8;
        if (posicion == 3) return 6;
        return 0;
    }

    private void ordenarResultadosCarrera(List<model.Coche> coches) {
        int n = coches.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                model.Coche a = coches.get(j);
                model.Coche b = coches.get(j + 1);

                if (debeIntercambiarCarrera(a, b)) {
                    coches.set(j, b);
                    coches.set(j + 1, a);
                }
            }
        }
    }

    private boolean debeIntercambiarCarrera(model.Coche a, model.Coche b) {
        if (a.getKmRecorridos() < b.getKmRecorridos()) return true;
        if (a.getKmRecorridos() > b.getKmRecorridos()) return false;

        if (a.getTiempoTotalSegundos() > b.getTiempoTotalSegundos()) return true;
        if (a.getTiempoTotalSegundos() < b.getTiempoTotalSegundos()) return false;

        return a.getDorsal() > b.getDorsal();
    }
}

