package estadoPartido;

import apuesta.Apuesta;

public class NoComenzado extends EstadoPartido {

    @Override
    public void activarApuesta(Apuesta apuesta) {
        apuesta.getEstado().activarApuesta();
    }

    @Override
    public double gananciaBruta(Apuesta apuesta) {
        System.out.println("\n No se puede obtener la ganancia bruta del: " + partido.getDeporte() +" "+ partido.getEstado() + " si no ha finalizado \n");
        return 0;
    }

    @Override
    public double gananciaNeta(Apuesta apuesta) {
        System.out.println("\n No se puede obtener la ganancia neta del: " + partido.getDeporte() +" "+ partido.getEstado() + " si no ha finalizado \n");
        return 0;
    }

    @Override
    protected void penalidadPorCancelarApuesta(Apuesta apuesta) {
        apuesta.penalidadPorCancelarApuesta(200);
    }

    @Override
    public boolean estaEnJuego() {
        return false;
    }

    @Override
    public boolean noHaComenzado() {
        return true;
    }

    @Override
    public boolean haFinalizado() {
        return false;
    }
}
