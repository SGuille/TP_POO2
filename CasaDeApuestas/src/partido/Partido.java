package partido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import algoritmoProbabilidad.AlgoritmoProbabilidad;
import apuesta.Apuesta;
import deporte.Deporte;
import estadoPartido.*;
import oponente.Oponente;
import resultadoPartido.*;

public class Partido extends Observable {

    private Oponente local;
    private Oponente visitante;
    private Deporte deporte;
	private List<Oponente> oponentes;
	private LocalDateTime fechaYHora;
	private String lugar;
    private EstadoPartido estado;
    private ResultadoPartido resultado;
    private Integer golesLocal, golesVisitante;

    public Partido(Deporte deporte, LocalDateTime fechaYHora, String lugar, Oponente local, Oponente visitante) {

        this.deporte = deporte;
        this.fechaYHora = fechaYHora;
        this.lugar = lugar;
        this.local = local;
        this.visitante = visitante;
        this.oponentes = new ArrayList<>();
        this.setEstado(new NoComenzado());
        this.golesLocal = 0;
        this.golesVisitante = 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public Deporte getDeporte() { return deporte; }

	public LocalDateTime getFechaYHora() { return fechaYHora; }

	public String getLugar() { return lugar; }

	public Oponente getLocal() { return local; }

    public Oponente getVisitante() { return visitante; }

    public ResultadoPartido getResultado() {
        this.setGanador();
        return resultado.getResultado(this);
    }

    public void setResultado(ResultadoPartido resultado) {
        this.resultado = resultado;
    }

    public List<Oponente> getOponentes() {
	    oponentes.add(local);
	    oponentes.add(visitante);
	    return oponentes;
    }

    public EstadoPartido getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoPartido estado) {
        this.estado = estado;
        estado.setPartido(this);
    }

    public Integer getGolesLocal() {
        return this.golesLocal;
    }

    public Integer getGolesVisitante() {
        return this.golesVisitante;
    }

    public void anotarGolLocal(Integer gol) { this.golesLocal += gol; }

    public void anotarGolVisitante(Integer gol) { this.golesVisitante += gol; }

    private void setGanador() {
        if (this.getGolesLocal() > this.getGolesVisitante()) {
            this.setResultado(this.getResultadoGanaLocal());
        }else{
            if (this.getGolesVisitante() > this.getGolesLocal()) {
                this.setResultado(this.getResultadoGanaVisitante());
            }else{
                this.setResultado(this.getResultadoEmpate());
            }
        }
    }

    public ResultadoPartido getResultadoGanaLocal() {
        return new GanaLocal();
    }
    public ResultadoPartido getResultadoGanaVisitante() {
        return new GanaVisitante();
    }
    public ResultadoPartido getResultadoEmpate() {
        return new Empate();
    }

    public Oponente ganador() {
        return resultado.ganador(this);
    }

    public void comenzar() {
        this.setEstado(new EnJuego());
        this.notificar(getEstado());
    }

    public void finalizar() {
        this.setEstado(new Finalizado());
        this.notificar(getEstado());
    }

    private void notificar(EstadoPartido estado) {
        this.setChanged();
        this.notifyObservers(estado);
    }

    public double gananciaBruta(Apuesta apuesta) { return getEstado().gananciaBruta(apuesta); }
    public double gananciaNeta(Apuesta apuesta) { return getEstado().gananciaNeta(apuesta); }

    public boolean esResultadoAcertado(ResultadoPartido resultadoPartido) {
        return getResultado().getType().equals(resultadoPartido.getType());
    }

    public void cancelarApuesta(Apuesta apuesta) {
        getEstado().cancelarApuesta(apuesta);
    }
    public void activarApuesta(Apuesta apuesta) {
        getEstado().activarApuesta(apuesta);
    }

    public boolean ganaLocal() {
        return getGolesLocal() > getGolesVisitante();
    }

    public boolean empate() {
        return getGolesLocal() == getGolesVisitante();
    }

    public boolean ganaVisitante() {
        return getGolesVisitante() > getGolesLocal();
    }

    public boolean tieneOponente(Oponente oponente) {
        return getOponentes().contains(oponente);
    }

    public float probabilidad(AlgoritmoProbabilidad algoritmoProbabilidad, Oponente local, Oponente visitante) {
        return getResultado().probabilidad(algoritmoProbabilidad, local, visitante);
    }
}
