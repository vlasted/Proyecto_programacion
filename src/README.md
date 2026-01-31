# Simulador de carreras (Programación)

## Estructura
- `src/model`: clases de dominio (`Coche`, `Carrera`, `Campeonato`, resultados…)
- `src/controller`: lógica de simulación (`CarreraController`, `CampeonatoController`)
- `src/util`: utilidades (entrada por consola)
- `src/app/Main.java`: punto de entrada

## Cómo compilar y ejecutar

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.Main
```

## Notas
- Cada vuelta incrementa kilómetros aleatorios entre 20 y 50.
- La carrera termina cuando algún coche alcanza el objetivo de KM.
- Puntuación por carrera: 1º 10, 2º 8, 3º 6 (resto 0).
- Se muestra el podio y la clasificación general tras cada carrera.
