package model;

import java.util.Objects;

public class Coche {
    private final String marca;
    private final String modelo;
    private final String piloto;
    private final int dorsal;

    private int kmRecorridos;
    private long tiempoTotalSegundos;
    private int puntosTotales;

    private final int velocidadMediaKmh;

    public Coche(String marca, String modelo, String piloto, int dorsal, int velocidadMediaKmh) {
        this.marca = Objects.requireNonNull(marca);
        this.modelo = Objects.requireNonNull(modelo);
        this.piloto = Objects.requireNonNull(piloto);
        this.dorsal = dorsal;
        this.velocidadMediaKmh = velocidadMediaKmh;

        this.kmRecorridos = 0;
        this.tiempoTotalSegundos = 0;
        this.puntosTotales = 0;
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getPiloto() { return piloto; }
    public int getDorsal() { return dorsal; }

    public int getKmRecorridos() { return kmRecorridos; }
    public long getTiempoTotalSegundos() { return tiempoTotalSegundos; }
    public int getPuntosTotales() { return puntosTotales; }

    public int getVelocidadMediaKmh() { return velocidadMediaKmh; }

    public void sumarKm(int km) {
        if (km < 0) throw new IllegalArgumentException("km no puede ser negativo");
        this.kmRecorridos += km;
    }

    public void sumarTiempoPorTramo(int kmTramo) {
        double horas = (double) kmTramo / (double) velocidadMediaKmh;
        long segundos = Math.max(1L, Math.round(horas * 3600.0));
        this.tiempoTotalSegundos += segundos;
    }

    public void resetearParaNuevaCarrera() {
        this.kmRecorridos = 0;
        this.tiempoTotalSegundos = 0;
    }

    public void sumarPuntos(int puntos) {
        if (puntos < 0) throw new IllegalArgumentException("puntos no puede ser negativo");
        this.puntosTotales += puntos;
    }

    public String nombreCoche() {
        return marca + " " + modelo;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "marca=" + marca +
                ", modelo=" + modelo +
                ", piloto=" + piloto +
                ", dorsal=" + dorsal +
                ", kmRecorridos=" + kmRecorridos +
                ", tiempoTotalSegundos=" + tiempoTotalSegundos +
                ", puntosTotales=" + puntosTotales +
                '}';
    }
}
