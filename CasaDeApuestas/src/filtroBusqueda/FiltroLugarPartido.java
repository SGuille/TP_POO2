package filtroBusqueda;

import eventoDeportivo.EventoDeportivo;

public class FiltroLugarPartido implements Filtro {
    private final String lugar;

    public FiltroLugarPartido(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public Boolean evaluar(EventoDeportivo eventoDeportivo) {
        return eventoDeportivo.getPartido().getLugar().equals(this.lugar);
    }
}
