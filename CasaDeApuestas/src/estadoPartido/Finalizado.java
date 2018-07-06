package estadoPartido;

import apuesta.Apuesta;

public class Finalizado extends EstadoPartido {

    @Override
    public void cancelarApuesta(Apuesta apuesta) {
        System.out.println("\n No se puede cancelar una apuesta cuando el: " + partido.getDeporte() + " está finalizado \n");
    }

    @Override
    public double gananciaBruta(Apuesta apuesta) { return apuesta.gananciaBrutaObtenida(); }

    @Override
    public double gananciaNeta(Apuesta apuesta) { return apuesta.gananciaNetaObtenida(); }

    @Override
    public void activarApuesta(Apuesta apuesta) {
        System.out.println("\n No se puede activar, porque el: " + partido.getDeporte() + " ya está finalizado \n");
    }

    @Override
    protected void penalidadPorCancelarApuesta(Apuesta apuesta) { }
}
