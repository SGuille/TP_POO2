package estadoPartido;

import apuesta.Apuesta;

public class EnJuego extends EstadoPartido {

    @Override
    public void activarApuesta(Apuesta apuesta) {
        System.out.println("\n No se puede reactivar una apuesta si el "+ partido.getDeporte() +" ya está en juego \n");
    }

    @Override
    public double gananciaBruta(Apuesta apuesta) {
        System.out.println("\n No se puede obtener la ganancia bruta del "+ partido.getDeporte() +" porque está en " +
                partido.getEstado() + " y no ha finalizado \n");
        return 0;
    }

    @Override
    public double gananciaNeta(Apuesta apuesta) {
        System.out.println("\n No se puede obtener la ganancia neta del "+ partido.getDeporte() +" porque está en " +
                partido.getEstado() + " y no ha finalizado \n");
        return 0;
    }

    @Override
    protected void penalidadPorCancelarApuesta(Apuesta apuesta) {
        apuesta.penalidadPorCancelarApuesta(apuesta.getMontoApostado() * 0.30);
    }

    @Override
    public boolean estaEnJuego() {
        return true;
    }

    @Override
    public boolean noHaComenzado() {
        return false;
    }

    @Override
    public boolean haFinalizado() {
        return false;
    }
}
