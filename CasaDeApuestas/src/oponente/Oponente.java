package oponente;

import Historial.HistorialDeEnfrentamientos;
import partido.Partido;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Oponente {

    private final String nombre;
    private HistorialDeEnfrentamientos historialDeEnfrentamientos;

    public Oponente(String nombre) {

        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    public List<Partido> partidosContra(Oponente oponente) {
        return this.getHistorialDeEnfrentamientos().stream().filter(partido -> partido.tieneOponente(oponente)).collect(Collectors.toList());
    }

    public List<Partido> partidosGanadosPorLocalContra(Oponente oponente) {
        return this.partidosGanadosPorLocal(this.partidosContra(oponente));
    }

    public List<Partido> partidosGanadosPorVisitanteContra(Oponente oponente) {
        return this.partidosGanadosPorVisitante(this.partidosContra(oponente));
    }

    public List<Partido> partidosEmpatadosContra(Oponente oponente) {
        return this.partidosEmpatados(this.partidosContra(oponente));
    }

    public List<Partido> partidosGanadosPorLocal(List<Partido> historial) {
        return historial.stream().filter(partido -> partido.ganaLocal()).collect(Collectors.toList());
    }

    public List<Partido> partidosGanadosPorVisitante(List<Partido> historial) {
        return historial.stream().filter(partido -> partido.ganaVisitante()).collect(Collectors.toList());
    }

    public List<Partido> partidosEmpatados(List<Partido> historial) {
        return historial.stream().filter(partido -> partido.empate()).collect(Collectors.toList());
    }

    public int cantidadPartidosContra(Oponente oponente) {
        return this.partidosContra(oponente).size();
    }

    public int cantidadPartidosGanadosPorLocalContra(Oponente oponente) {
        return this.partidosGanadosPorLocalContra(oponente).size();
    }

    public int cantidadPartidosGanadosPorVisitanteContra(Oponente oponente) {
        return this.partidosGanadosPorVisitanteContra(oponente).size();
    }

    public int cantidadPartidosEmpatadosContra(Oponente oponente) {
        return this.partidosEmpatadosContra(oponente).size();
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Partido> getHistorialDeEnfrentamientos() {
        return this.historialDeEnfrentamientos.getHistorial().stream().filter(partido -> partido.tieneOponente(this)).collect(Collectors.toList());
    }

    public void setHistorialDeEnfrentamientos(HistorialDeEnfrentamientos historialDePartidos) {
        this.historialDeEnfrentamientos = historialDePartidos;
    }
}
