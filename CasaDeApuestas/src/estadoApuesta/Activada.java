package estadoApuesta;

import apuesta.Apuesta;

public class Activada extends EstadoApuesta {

    public Activada(Apuesta apuesta) {
        super(apuesta);
    }

    @Override
    public void activarApuesta() {
        System.out.println("\n La apuesta: " + apuesta + " ya está activada \n");
    }

    @Override
    public void cancelarApuesta() {
        apuesta.setEstado(apuesta.estadoCancelada());
    }
}
