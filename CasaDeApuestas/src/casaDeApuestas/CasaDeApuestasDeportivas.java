package casaDeApuestas;
import algoritmoProbabilidad.AlgoritmoProbabilidad;
import apuesta.Apuesta;
import balanceNotifier.BalanceNotifier;
import eventoDeportivo.EventoDeportivo;
import estadoPartido.EnJuego;
import filtroBusqueda.Filtro;
import partido.Partido;
import usuario.Usuario;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CasaDeApuestasDeportivas implements Observer {

    private final List<Apuesta> apuestas = new ArrayList<>();
    private BalanceNotifier balanceNotifier;
    private AlgoritmoProbabilidad algoritmoProbabilidad;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    private void enviarBalanceDelMes(Usuario usuario1, Integer mes, BigDecimal balanceMensual) {
        balanceNotifier.notifyBalance(usuario1, mes, balanceMensual);
    }

    public void enviarBalanceDelMesATodosLosUsuarios(Integer mes) {
        this.apuestas.stream().map(Apuesta::getApostador).collect(Collectors.toList()).forEach(usuario ->
                this.enviarBalanceDelMes(usuario, mes, this.balanceMensual(usuario, mes)));
    }

    private BigDecimal balanceMensual(Usuario usuario, Integer mes) {
        return BigDecimal.valueOf(this.apuestasDeApostadorEnMes(usuario, mes).stream().mapToDouble(Apuesta::getGananciaNeta).sum());
    }

    private Set<Apuesta> apuestasDeApostadorEnMes(Usuario usuario, Integer mes) {
        return Stream.concat(this.apuestasDelMes(mes).stream(), this.apuestaDeApostador(usuario).stream()).collect(Collectors.toSet());
    }

    private List<Apuesta> apuestaDeApostador(Usuario usuario) {
        return this.apuestas.stream().filter(apuesta -> apuesta.getApostador().equals(usuario)).collect(Collectors.toList());
    }

    private List<Apuesta> apuestasDelMes(Integer mes) {
        return this.apuestas.stream().filter(apuesta -> apuesta.getMes()==(mes)).collect(Collectors.toList());
    }


    public void agregarApuesta(Apuesta apuesta) {

        this.apuestas.add(apuesta);
        apuesta.setCasaDeApuestasDeportivas(this);
    }

    public AlgoritmoProbabilidad getAlgoritmoProbabilidad() {
        return algoritmoProbabilidad;
    }

    public void setAlgoritmoDeProbabilidad(AlgoritmoProbabilidad algoritmoProbabilidad) {
        this.algoritmoProbabilidad = algoritmoProbabilidad;
    }

    public void setBalanceNotifier(BalanceNotifier balanceNotifier) {
        this.balanceNotifier = balanceNotifier;
    }

    @Override
    public void update(Observable observable, Object arg) {
        Partido partido = (Partido) observable;
        if(partido.estaEnJuego()) {
            System.out.println("La " + this + " ha sido notificada de que el " + partido.getDeporte() + " está " + arg);
        }
    }

    public Set<EventoDeportivo> buscarEventos(Filtro filtro) {
        return apuestas.stream().map(apuesta -> apuesta.getEventoDeportivo()).filter(eventoDeportivo -> filtro.evaluar(eventoDeportivo)).collect(Collectors.toSet());
    }
}