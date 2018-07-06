package apuesta;

import casaDeApuestas.CasaDeApuestasDeportivas;
import estadoApuesta.*;
import eventoDeportivo.EventoDeportivo;
import oponente.Oponente;
import partido.Partido;
import resultadoPartido.ResultadoPartido;
import usuario.Usuario;

import java.util.Observer;

public abstract class Apuesta {

    private final Integer mes;
    private EstadoApuesta estado;
    protected double gananciaBruta;
	protected double gananciaNeta;
    protected EventoDeportivo eventoDeportivo;
    private CasaDeApuestasDeportivas casaDeApuestasDeportivas;
    private Usuario apostador;
    private double montoApostado;
    private ResultadoPartido resultadoApostado;
    private Oponente oponenteApostado;

    public Apuesta(EventoDeportivo eventoDeportivo, Integer mes) {

        this.estado = new Activada(this);
        this.eventoDeportivo = eventoDeportivo;
        this.mes = mes;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    protected double gananciaBruta() { return gananciaBrutaPartido(); }

    protected double gananciaNeta() {
        return gananciaNetaPartido();
    }

    public double calcularGananciaBruta() {

        if (this.esAcertada()) {
            this.setGananciaBruta();
            this.getApostador().incrementarGananciaBruta(this.gananciaBruta);
        }else{
            this.gananciaBruta = 0;
        }
        return this.gananciaBruta;
    }

    public double calcularGananciaNeta() {
        if (this.esAcertada()) {
            this.setGananciaNeta();
            this.getApostador().incrementarGananciaNeta(this.gananciaNeta);
        }else{
            this.gananciaNeta = 0;
        }
        return this.gananciaNeta;
    }

    protected abstract void setGananciaBruta();
    protected abstract void setGananciaNeta();

    public abstract  void activar();
    public abstract void cancelar();

    public EstadoApuesta estadoActivada() { return new Activada(this);}
    public EstadoApuesta estadoCancelada() { return new Cancelada(this);}

    public boolean esAcertada() {
        return this.resultadoDePartidoAcertado(resultadoApostado);
    }

    public void realizarApuesta(Usuario apostador, double monto, ResultadoPartido resultadoAlQueApuesta, Oponente oponenteApostado) {
        this.apostador = apostador;
        this.montoApostado = monto;
        this.resultadoApostado = resultadoAlQueApuesta;
        this.oponenteApostado = oponenteApostado;
        this.agregarObserverDePartido(apostador);
    }

    private void decrementarGananciaBrutaDeApostador(double monto) {
        getApostador().decrementarGananciaBruta(monto);
    }

    private void decrementarGananciaNetaDeApostador(double monto) {
        getApostador().decrementarGananciaNeta(monto);
    }

    public void penalidadPorCancelarApuesta(double monto) {
        decrementarGananciaBrutaDeApostador(monto);
        decrementarGananciaNetaDeApostador(monto);
    }

    public EstadoApuesta getEstado(){return this.estado;}

    public double gananciaBrutaObtenida() {
        return Math.round(this.cuotaEvento() * this.montoApostado);
    }

    public double gananciaNetaObtenida() {
        return Math.round((this.cuotaEvento() * this.montoApostado) - this.montoApostado);
    }

    private double cuotaEvento() {
        return eventoDeportivo.calcularCuota(this.getOponenteApostado(), casaDeApuestasDeportivas.getAlgoritmoProbabilidad());
    }

    public EventoDeportivo getEventoDeportivo() { return this.eventoDeportivo;}

    public void setEstado(EstadoApuesta estado) {
        this.estado = estado;
    }

    public Usuario getApostador() { return this.apostador; }

    public double getMontoApostado() {
        return this.montoApostado;
    }

    public void setCasaDeApuestasDeportivas(CasaDeApuestasDeportivas casaDeApuestasDeportivas) {
        this.casaDeApuestasDeportivas = casaDeApuestasDeportivas;
        this.agregarObserverDePartido(casaDeApuestasDeportivas);
    }

    public Oponente getOponenteApostado() {
        return this.oponenteApostado;
    }

    public Integer getMes() {
        return this.mes;
    }

    public double getGananciaBruta() {
        return Math.ceil(this.gananciaBruta);
    }

    public double getGananciaNeta() {
        return Math.floor(this.gananciaNeta);
    }

    private double gananciaBrutaPartido() {
        return partidoDeEvento().gananciaBruta(this);
    }

    private double gananciaNetaPartido() { return partidoDeEvento().gananciaNeta(this); }

    private void agregarObserverDePartido(Observer observer) {
        partidoDeEvento().addObserver(observer);
    }

    private boolean resultadoDePartidoAcertado(ResultadoPartido resultadoPartido) {
        return partidoDeEvento().esResultadoAcertado(resultadoPartido);
    }

    protected Partido partidoDeEvento() {
        return getEventoDeportivo().getPartido();
    }
}
