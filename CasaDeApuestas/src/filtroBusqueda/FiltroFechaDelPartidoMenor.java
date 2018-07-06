package filtroBusqueda;

import eventoDeportivo.EventoDeportivo;

import java.time.LocalDateTime;

public class FiltroFechaDelPartidoMenor implements Filtro {
    private LocalDateTime fechaYHora;

    public FiltroFechaDelPartidoMenor(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public Boolean evaluar(EventoDeportivo eventoDeportivo) {
        return eventoDeportivo.getPartido().getFechaYHora().isBefore(this.fechaYHora);
    }
}
