package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Carrera {
    private final int numero;
    private final int distanciaKm;
    private final List<Coche> participantes;

    public Carrera(int numero, int distanciaKm, List<Coche> participantes) {
        if (numero <= 0) throw new IllegalArgumentException("El numero de carrera debe ser > 0");
        if (distanciaKm <= 0) throw new IllegalArgumentException("La distancia debe ser > 0");
        Objects.requireNonNull(participantes);

        this.numero = numero;
        this.distanciaKm = distanciaKm;
        this.participantes = new ArrayList<>(participantes);
    }

    public int getNumero() { return numero; }
    public int getDistanciaKm() { return distanciaKm; }

    public List<Coche> getParticipantes() {
        return Collections.unmodifiableList(participantes);
    }
}
