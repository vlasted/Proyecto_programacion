package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoCarrera {
    private final model.Carrera carrera;
    private final List<ResultadoParticipante> resultadosOrdenados;

    public ResultadoCarrera(model.Carrera carrera, List<ResultadoParticipante> resultadosOrdenados) {
        this.carrera = carrera;
        this.resultadosOrdenados = new ArrayList<>(resultadosOrdenados);
    }

    public model.Carrera getCarrera() { return carrera; }

    public List<ResultadoParticipante> getResultadosOrdenados() {
        return Collections.unmodifiableList(resultadosOrdenados);
    }
}

