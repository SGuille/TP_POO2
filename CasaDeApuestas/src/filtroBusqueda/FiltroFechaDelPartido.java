package filtroBusqueda;

import eventoDeportivo.EventoDeportivo;

import java.time.LocalDateTime;

public class FiltroFechaDelPartido implements Filtro {
    private LocalDateTime fechaYHora;

    public FiltroFechaDelPartido(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public Boolean evaluar(EventoDeportivo eventoDeportivo) {
        return eventoDeportivo.getPartido().getFechaYHora().equals(this.fechaYHora);
    }
}
