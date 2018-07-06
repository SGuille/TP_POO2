package filtroBusqueda;

import deporte.Deporte;
import eventoDeportivo.EventoDeportivo;

public class FiltroDeportePartido implements Filtro {
    private Deporte deporte;

    public FiltroDeportePartido(Deporte deporte) {
        this.deporte = deporte;
    }

    @Override
    public Boolean evaluar(EventoDeportivo eventoDeportivo) {
        return eventoDeportivo.getPartido().getDeporte().equals(this.deporte);
    }
}
