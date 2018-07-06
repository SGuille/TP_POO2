package filtroBusqueda;

import eventoDeportivo.EventoDeportivo;
import oponente.Oponente;

public class FiltroOponente implements Filtro {
    private Oponente oponente;

    public FiltroOponente(Oponente oponente) {
        this.oponente = oponente;
    }

    @Override
    public Boolean evaluar(EventoDeportivo eventoDeportivo) {
        return eventoDeportivo.getPartido().getVisitante().equals(this.oponente);
    }
}
