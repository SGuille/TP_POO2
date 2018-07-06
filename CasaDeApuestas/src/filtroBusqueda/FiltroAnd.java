package filtroBusqueda;

import eventoDeportivo.EventoDeportivo;

public class FiltroAnd extends FiltroCompuesto {
    public FiltroAnd(Filtro filtroIzquierda, Filtro filtroDerecha) {
        super(filtroIzquierda, filtroDerecha);
    }

    @Override
    public Boolean evaluar(EventoDeportivo eventoDeportivo) {
        return filtroIzquierda.evaluar(eventoDeportivo) && filtroDerecha.evaluar(eventoDeportivo);
    }
}
