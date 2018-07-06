package estadoApuesta;

import apuesta.Apuesta;

public abstract class EstadoApuesta {

    protected Apuesta apuesta;

    public EstadoApuesta(Apuesta apuesta) {
        this.apuesta = apuesta;
    }

    public abstract void activarApuesta();
    public abstract void cancelarApuesta();
}
