package estadoPartido;

import apuesta.Apuesta;
import partido.Partido;

public abstract class EstadoPartido {

    protected Partido partido;

    public abstract void activarApuesta(Apuesta apuesta);
    public abstract double gananciaBruta(Apuesta apuesta);
    public abstract double gananciaNeta(Apuesta apuesta);
    protected abstract void penalidadPorCancelarApuesta(Apuesta apuesta);
    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public void cancelarApuesta(Apuesta apuesta) {
        apuesta.getEstado().cancelarApuesta();
        this.penalidadPorCancelarApuesta(apuesta);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
