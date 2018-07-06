package algoritmoProbabilidad;

import oponente.Oponente;
import partido.Partido;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HistoriaReciente extends AlgoritmoProbabilidad {

    public float probabilidadDeGanarLocal(Oponente local, Oponente visitante) {
        List<Partido> ganados;

        List<Partido> historialOrdenado = this.historialOrdenadoPorFechaMasReciente(local);
        ganados = local.partidosGanadosPorLocal(this.diezPartidosMasRecientes(historialOrdenado));

        return this.cantidadPartidos(ganados) / 10f;
    }

    public float probabilidadDeGanarVisitante(Oponente local, Oponente visitante) {
        List<Partido> ganados;

        List<Partido> historialOrdenado = this.historialOrdenadoPorFechaMasReciente(local);
        ganados = local.partidosGanadosPorVisitante(this.diezPartidosMasRecientes(historialOrdenado));

        return this.cantidadPartidos(ganados) / 10f;
    }

    public float probabilidadDeEmpatar(Oponente local, Oponente visitante) {
        return (this.probabilidadDeEmpatar(local) + this.probabilidadDeEmpatar(visitante)) / 2f;
    }

    public float probabilidadDeEmpatar(Oponente oponente) {
        List<Partido> empatados;

        List<Partido> historialOrdenado = this.historialOrdenadoPorFechaMasReciente(oponente);
        empatados = oponente.partidosEmpatados(this.diezPartidosMasRecientes(historialOrdenado));

        return this.cantidadPartidos(empatados) / 10f;
    }

    public List<Partido> historialOrdenado(List<Partido> historial) {
        return historial.stream().sorted(Comparator.comparing(Partido::getFechaYHora)).collect(Collectors.toList());
    }

    public List<Partido> historialOrdenadoPorFechaMasReciente(Oponente oponente) {
        List<Partido> historialOrdenado;
        List<Partido> historial;

        historial = oponente.getHistorialDeEnfrentamientos();
        Collections.reverse(this.historialOrdenado(historial));
        historialOrdenado = this.historialOrdenado(historial);

        return historialOrdenado;
    }

    public List<Partido> diezPartidosMasRecientes(List<Partido> historial) {
        return this.cantidadPartidos(historial) >= 10 ? historial.subList(0,10) : historial.subList(0, this.cantidadPartidos(historial));
    }

    public Integer cantidadPartidos(List<Partido> partidos) {
        return partidos.size();
    }

}
