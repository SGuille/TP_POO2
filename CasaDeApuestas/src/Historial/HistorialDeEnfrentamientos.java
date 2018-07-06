package Historial;

import java.util.ArrayList;
import java.util.List;

import partido.Partido;

public class HistorialDeEnfrentamientos {

    private List<Partido> historial = new ArrayList<>();

    public void setHistorial(List<Partido> historial) {
        this.historial = historial;
    }

    public List<Partido> getHistorial() {
        return this.historial;
    }

    public void agregarPartidoJugado(Partido partido) {
        this.historial.add(partido);
    }

}
