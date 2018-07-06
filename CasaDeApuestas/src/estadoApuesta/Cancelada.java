package estadoApuesta;

import apuesta.Apuesta;

public class Cancelada extends EstadoApuesta {

    public Cancelada(Apuesta apuesta) {
        super(apuesta);
    }

    @Override
    public void activarApuesta() {
        apuesta.setEstado(apuesta.estadoActivada());
    }

    @Override
    public void cancelarApuesta() {
        System.out.println("La apuesta: " + apuesta + " ya está cancelada");
    }
}
