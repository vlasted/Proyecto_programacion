package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Campeonato {
    private final List<Carrera> carreras;
    private final List<Coche> participantes;

    public Campeonato(List<Coche> participantes, List<Carrera> carreras) {
        Objects.requireNonNull(participantes);
        Objects.requireNonNull(carreras);

        this.participantes = new ArrayList<>(participantes);
        this.carreras = new ArrayList<>(carreras);
    }

    public List<Carrera> getCarreras() {
        return Collections.unmodifiableList(carreras);
    }

    public List<Coche> getParticipantes() {
        return Collections.unmodifiableList(participantes);
    }
}

