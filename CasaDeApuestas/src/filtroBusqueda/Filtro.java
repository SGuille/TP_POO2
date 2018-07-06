package filtroBusqueda;

import eventoDeportivo.EventoDeportivo;

public interface Filtro {

    Boolean evaluar(EventoDeportivo eventoDeportivo);
}
