package model;

public class ResultadoParticipante {
    private final model.Coche coche;
    private final int posicion;
    private final int puntosEnCarrera;

    public ResultadoParticipante(model.Coche coche, int posicion, int puntosEnCarrera) {
        this.coche = coche;
        this.posicion = posicion;
        this.puntosEnCarrera = puntosEnCarrera;
    }

    public model.Coche getCoche() { return coche; }
    public int getPosicion() { return posicion; }
    public int getPuntosEnCarrera() { return puntosEnCarrera; }
}
