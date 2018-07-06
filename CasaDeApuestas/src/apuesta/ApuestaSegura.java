package apuesta;

import eventoDeportivo.EventoDeportivo;

public class ApuestaSegura extends Apuesta {

    public ApuestaSegura(EventoDeportivo eventoDeportivo, Integer mes) {
        super(eventoDeportivo, mes);
    }

    @Override
    protected void setGananciaBruta() {
        this.gananciaBruta = this.gananciaBruta() * 0.85;
    }

    @Override
    protected void setGananciaNeta() {
        this.gananciaNeta = this.gananciaNeta() * 0.85;
    }

    public void cancelar() { this.cancelarApuestaPartido(); }

    public void activar() {
        this.activarApuestaPartido();
    }

    private void cancelarApuestaPartido() {
        partidoDeEvento().cancelarApuesta(this);
    }

    private void activarApuestaPartido() {
        partidoDeEvento().activarApuesta(this);
    }
}
