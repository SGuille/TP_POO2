package filtroBusqueda;

import eventoDeportivo.EventoDeportivo;

import java.time.LocalDateTime;

public class FiltroFechaDelPartidoMayor implements Filtro {
    private LocalDateTime fechaYHora;

    public FiltroFechaDelPartidoMayor(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public Boolean evaluar(EventoDeportivo eventoDeportivo) {
        return eventoDeportivo.getPartido().getFechaYHora().isAfter(this.fechaYHora);
    }
}
