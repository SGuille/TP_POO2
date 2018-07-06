package apuesta;

import eventoDeportivo.EventoDeportivo;

public class ApuestaFinal extends Apuesta {

    public ApuestaFinal(EventoDeportivo eventoDeportivo, Integer mes) {
        super(eventoDeportivo, mes);
    }

    @Override
    protected void setGananciaBruta() {
        this.gananciaBruta = this.gananciaBruta();
    }

    @Override
    protected void setGananciaNeta() {
        this.gananciaNeta = this.gananciaNeta();
    }

    public void cancelar() {
        System.out.println("\n La: " + this + " no puede ser cancelada \n");
    }

    public void activar() {
        System.out.println("La " + this + " ya está activada, ya que la misma no pudo haber sido cancelada ");
    }
}
