package algoritmoProbabilidad;

import oponente.Oponente;
import partido.Partido;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HistoriaReciente extends AlgoritmoProbabilidad {

    public float probabilidadDeGanarLocal(Oponente local, Oponente visitante) {
        historial = local.getHistorialDeEnfrentamientos();
        Collections.reverse(this.historialOrdenado(historial));
        historialOrdenado = this.historialOrdenado(historial);
        ganados = local.partidosGanadosPorLocal(this.primerosDiezPartidos(historialOrdenado));

        return this.cantidadPartidos(ganados) / 10f;
    }

    public float probabilidadDeGanarVisitante(Oponente local, Oponente visitante) {
        historial = local.getHistorialDeEnfrentamientos();
        Collections.reverse(this.historialOrdenado(historial));
        historialOrdenado = this.historialOrdenado(historial);
        ganados = local.partidosGanadosPorVisitante(this.primerosDiezPartidos(historialOrdenado));

        return this.cantidadPartidos(ganados) / 10f;
    }

    public float probabilidadDeEmpatar(Oponente local, Oponente visitante) {

        return (this.probabilidadDeEmpatarAux(local) + this.probabilidadDeEmpatarAux(visitante)) / 2f;
    }

    public float probabilidadDeEmpatarAux(Oponente oponente) {

        historial = oponente.getHistorialDeEnfrentamientos();
        Collections.reverse(this.historialOrdenado(historial));
        historialOrdenado = this.historialOrdenado(historial);
        empatados = oponente.partidosEmpatados(this.primerosDiezPartidos(historialOrdenado));

        return this.cantidadPartidos(empatados) / 10f;
    }

    public List<Partido> historialOrdenado(List<Partido> historial) {
        return historial.stream().sorted(Comparator.comparing(Partido::getFechaYHora)).collect(Collectors.toList());
    }

    public List<Partido> primerosDiezPartidos(List<Partido> historial) {
        return this.cantidadPartidos(historial) >= 10 ? historial.subList(0,10) : historial.subList(0, this.cantidadPartidos(historial));
    }

    public Integer cantidadPartidos(List<Partido> partidos) {
        return partidos.size();
    }
}
