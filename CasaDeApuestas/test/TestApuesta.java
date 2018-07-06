import Historial.HistorialDeEnfrentamientos;
import algoritmoProbabilidad.CompetenciaHistoricaDirecta;
import algoritmoProbabilidad.HistoriaReciente;
import apuesta.ApuestaFinal;
import apuesta.ApuestaSegura;
import balanceNotifier.BalanceNotifierAdapter;
import balanceNotifier.EmailBalanceNotifier;
import balanceNotifier.TextMessageBalanceNotifier;
import casaDeApuestas.*;
import deporte.DeporteGrupal;
import eventoDeportivo.EventoDeportivo;
import oponente.Equipo;
import org.junit.Before;
import org.junit.Test;
import partido.*;
import resultadoPartido.*;
import usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class TestApuesta {

    private ApuestaFinal apuestaFinal, apuestaFinal2;
    private ApuestaSegura apuestaSegura, apuestaSegura2;
    private EventoDeportivo eventoDeportivo, eventoDeportivo2, eventoDeportivo3;
    private Partido bocaVsRiver, riverVsBoca, bocaVsRiverEmpate;
    private Usuario usuario;
    private ResultadoPartido ganaLocal, empate, ganaVisitante;
    private Equipo boca, river;
    private DeporteGrupal futbol;
    private LocalDateTime fechaYHora;
    private List<String> posiblesResultados;
    private CompetenciaHistoricaDirecta competenciaHistoricaDirecta;
    private HistoriaReciente historiaReciente;
    private CasaDeApuestasDeportivas casaDeApuestasDeportivas;
    private HistorialDeEnfrentamientos historialDeEnfrentamientos;
    private TextMessageBalanceNotifier textMessageBalanceNotifier;
    private BalanceNotifierAdapter balanceNotifierAdapter;
    private EmailBalanceNotifier emailBalance;

    @Before
    public void setUp() {

        ganaLocal = new GanaLocal();
        empate = new Empate();
        ganaVisitante = new GanaVisitante();

        posiblesResultados = new ArrayList<>();
        posiblesResultados.add("GanaLocal");
        posiblesResultados.add("GanaVisitante");
        posiblesResultados.add("Empate");

        fechaYHora = LocalDateTime.now();

        futbol = new DeporteGrupal("futbol", posiblesResultados);

        boca = new Equipo("Boca");
        river = new Equipo("River");

        usuario = new Usuario("myUsername", "myPassword");

        bocaVsRiver = new Partido(futbol, fechaYHora, "Avellaneda", boca, river);
        bocaVsRiverEmpate = new Partido(futbol, fechaYHora, "Avellaneda", river, boca);
        riverVsBoca = new Partido(futbol, fechaYHora, "Lanus", boca, river);

        eventoDeportivo = new EventoDeportivo("Superliga", bocaVsRiver);
        eventoDeportivo2 = new EventoDeportivo("Superliga", riverVsBoca);
        eventoDeportivo3 = new EventoDeportivo("Superliga", bocaVsRiverEmpate);

        apuestaFinal = new ApuestaFinal(eventoDeportivo, 5);
        apuestaFinal2 = new ApuestaFinal(eventoDeportivo3, 5);
        apuestaSegura = new ApuestaSegura(eventoDeportivo2, 5);
        apuestaSegura2 = new ApuestaSegura(eventoDeportivo, 5);

        competenciaHistoricaDirecta = new CompetenciaHistoricaDirecta();
        historiaReciente = new HistoriaReciente();

        emailBalance = new EmailBalanceNotifier();
        balanceNotifierAdapter = new BalanceNotifierAdapter(emailBalance);
        textMessageBalanceNotifier = new TextMessageBalanceNotifier();
        casaDeApuestasDeportivas = new CasaDeApuestasDeportivas();
        historialDeEnfrentamientos = new HistorialDeEnfrentamientos();

        historialDeEnfrentamientos.agregarPartidoJugado(bocaVsRiver);
        historialDeEnfrentamientos.agregarPartidoJugado(riverVsBoca);
        historialDeEnfrentamientos.agregarPartidoJugado(bocaVsRiverEmpate);

        boca.setHistorialDeEnfrentamientos(historialDeEnfrentamientos);
        river.setHistorialDeEnfrentamientos(historialDeEnfrentamientos);
    }

    @Test
    public void testPrimero_ApuestaGanadaConAlgoritmoProbabilidadCompetenciaHistoricaDirectaYApuestaFinalDadoUnPartidoFinalizado() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);

        usuario.apostar(apuestaFinal, 100, ganaLocal, boca);
        casaDeApuestasDeportivas.agregarApuesta(apuestaFinal);
        bocaVsRiver.comenzar();
        bocaVsRiver.anotarGolLocal(1);
        bocaVsRiver.finalizar();
        assertTrue(bocaVsRiver.getResultado() instanceof GanaLocal);
        assertTrue(apuestaFinal.esAcertada());
        assertEquals(eventoDeportivo.calcularCuota(boca, competenciaHistoricaDirecta), 1.9, 1);
        assertEquals(apuestaFinal.calcularGananciaBruta(), 167, 0);
        assertEquals(apuestaFinal.calcularGananciaNeta(), 67, 1);

        apuestaFinal.cancelar();

        assertEquals(apuestaFinal.getGananciaBruta(), 167, 1);
        assertEquals(apuestaFinal.getGananciaNeta(), 67, 1);

        assertEquals(usuario.getGananciaBruta(), 167, 0);
        assertEquals(usuario.getGananciaNeta(), 67, 1);

        casaDeApuestasDeportivas.setBalanceNotifier(textMessageBalanceNotifier);
        casaDeApuestasDeportivas.enviarBalanceDelMesATodosLosUsuarios(5);
    }

    @Test
    public void testSegundo_ApuestaGanadaConAlgoritmoProbabilidadCompetenciaHistoricaDirectaYApuestaFinalDadoUnPartidoNoComenzado() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);

        usuario.apostar(apuestaFinal2, 100, empate, boca);
        casaDeApuestasDeportivas.agregarApuesta(apuestaFinal2);
        assertTrue(bocaVsRiverEmpate.getResultado() instanceof Empate);
        assertEquals(apuestaFinal2.calcularGananciaBruta(), 0, 0);
        assertEquals(apuestaFinal2.calcularGananciaNeta(), 0, 0);

        apuestaFinal2.cancelar();

        assertEquals(apuestaFinal2.calcularGananciaBruta(), 0, 0);
        assertEquals(apuestaFinal2.calcularGananciaNeta(), 0, 0);

        casaDeApuestasDeportivas.setBalanceNotifier(balanceNotifierAdapter);
        casaDeApuestasDeportivas.enviarBalanceDelMesATodosLosUsuarios(5);
    }

    @Test
    public void testTercero_ApuestaPerdidaConAlgoritmoProbabilidadCompetenciaHistoricaDirectaYApuestaFinalDadoUnPartidoFinalizado() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);

        usuario.apostar(apuestaFinal, 100, ganaVisitante, boca);
        casaDeApuestasDeportivas.agregarApuesta(apuestaFinal);
        bocaVsRiver.anotarGolLocal(2);
        bocaVsRiver.finalizar();
        assertTrue(bocaVsRiver.getResultado()instanceof  GanaLocal);
        assertFalse(apuestaFinal.esAcertada());
        assertEquals(apuestaFinal.calcularGananciaBruta(), 0, 0.1);
        assertEquals(apuestaFinal.calcularGananciaNeta(), 0, 0.1);

        casaDeApuestasDeportivas.setBalanceNotifier(textMessageBalanceNotifier);
        casaDeApuestasDeportivas.enviarBalanceDelMesATodosLosUsuarios(5);
    }

    @Test
    public void testCuarto_ApuestaGanadaConAlgoritmoProbabilidadHistoriaRecienteyApuestaSeguraDadoUnPartidoFinalizado() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);

        usuario.apostar(apuestaSegura, 100, ganaVisitante, boca);
        casaDeApuestasDeportivas.agregarApuesta(apuestaSegura);
        riverVsBoca.anotarGolVisitante(2);
        riverVsBoca.finalizar();
        assertTrue(riverVsBoca.getResultado() instanceof GanaVisitante);
        assertTrue(apuestaSegura.esAcertada());
        assertEquals(eventoDeportivo2.calcularCuota(boca, historiaReciente), 1.9, 1);
        assertEquals(apuestaSegura.calcularGananciaBruta(), 161.5, 1);
        assertEquals(apuestaSegura.calcularGananciaNeta(), 76.5, 1);

        apuestaSegura.cancelar();

        assertEquals(apuestaSegura.getGananciaBruta(), 161.5, 1);
        assertEquals(apuestaSegura.getGananciaNeta(), 76.5, 1);

        assertEquals(usuario.getGananciaBruta(), 161.5, 1);
        assertEquals(usuario.getGananciaNeta(), 76.5, 1);

        casaDeApuestasDeportivas.setBalanceNotifier(balanceNotifierAdapter);
        casaDeApuestasDeportivas.enviarBalanceDelMesATodosLosUsuarios(5);
    }

    @Test
    public void testQuinto_ApuestaGanadaConAlgoritmoProbabilidadHistoriaRecienteyApuestaSeguraDadoUnPartidoEnJuego() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);

        usuario.apostar(apuestaSegura, 100, ganaVisitante, boca);
        casaDeApuestasDeportivas.agregarApuesta(apuestaSegura);
        riverVsBoca.comenzar();
        riverVsBoca.anotarGolVisitante(2);
        assertTrue(riverVsBoca.getResultado() instanceof GanaVisitante);
        assertTrue(apuestaSegura.esAcertada());
        assertEquals(apuestaSegura.calcularGananciaBruta(), 0, 0.1);
        assertEquals(apuestaSegura.calcularGananciaNeta(), 0, 0.1);

        apuestaSegura.cancelar();

        assertEquals(apuestaSegura.getGananciaBruta(), 0, 0.1);
        assertEquals(apuestaSegura.getGananciaNeta(), 0, 0.1);

        casaDeApuestasDeportivas.setBalanceNotifier(textMessageBalanceNotifier);
        casaDeApuestasDeportivas.enviarBalanceDelMesATodosLosUsuarios(5);
    }

    @Test
    public void testSexto_ApuestaPerdidaConAlgoritmoProbabilidadHistoriaRecienteyApuestaSeguraDadoUnPartidoFinalizado() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);

        usuario.apostar(apuestaSegura, 100, empate, boca);
        casaDeApuestasDeportivas.agregarApuesta(apuestaSegura);
        riverVsBoca.anotarGolLocal(3);
        riverVsBoca.anotarGolVisitante(1);
        riverVsBoca.finalizar();
        assertTrue(riverVsBoca.getResultado() instanceof GanaLocal);
        assertFalse(apuestaSegura.esAcertada());
        assertEquals(apuestaSegura.calcularGananciaBruta(), 0, 0.1);
        assertEquals(apuestaSegura.calcularGananciaNeta(), 0, 0.1);

        assertEquals(usuario.getGananciaBruta(), 0, 0);
        assertEquals(usuario.getGananciaNeta(), 0, 0);

        casaDeApuestasDeportivas.setBalanceNotifier(balanceNotifierAdapter);
        casaDeApuestasDeportivas.enviarBalanceDelMesATodosLosUsuarios(5);
    }

    @Test
    public void testSeptimo_ApuestaCanceladaConAlgoritmoProbabilidadHistoriaRecienteyApuestaSeguraDadoUnPartidoNoComenzado() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);

        usuario.apostar(apuestaSegura, 100, ganaVisitante, boca);
        casaDeApuestasDeportivas.agregarApuesta(apuestaSegura);
        assertTrue(riverVsBoca.getResultado() instanceof Empate);
        assertFalse(apuestaSegura.esAcertada());
        assertEquals(apuestaSegura.calcularGananciaBruta(), 0, 0.1);
        assertEquals(apuestaSegura.calcularGananciaNeta(), 0, 0.1);

        apuestaSegura.cancelar();

        riverVsBoca.comenzar();
        riverVsBoca.anotarGolVisitante(3);
        assertTrue(apuestaSegura.esAcertada());
        riverVsBoca.finalizar();

        assertEquals(apuestaSegura.calcularGananciaBruta(), 161.5, 0.1);
        assertEquals(apuestaSegura.calcularGananciaNeta(), 76, 1);

        assertEquals(usuario.getGananciaBruta(), 161.5, 0);
        assertEquals(usuario.getGananciaNeta(), 76, 1);

        casaDeApuestasDeportivas.setBalanceNotifier(textMessageBalanceNotifier);
        casaDeApuestasDeportivas.enviarBalanceDelMesATodosLosUsuarios(5);
    }

    @Test
    public void testOctavo_ApuestaGanadaConAlgoritmoProbabilidadCompetenciaHistoricaDirectaYApuestaSeguraDadoUnPartidoEnJuego() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);

        usuario.apostar(apuestaSegura2, 100, ganaLocal, boca);
        casaDeApuestasDeportivas.agregarApuesta(apuestaSegura2);
        bocaVsRiver.comenzar();
        bocaVsRiver.anotarGolLocal(2);
        assertTrue(bocaVsRiver.getResultado() instanceof GanaLocal);
        assertTrue(apuestaSegura2.esAcertada());
        assertEquals(apuestaSegura2.calcularGananciaBruta(), 0, 0);
        assertEquals(apuestaSegura2.calcularGananciaNeta(), 0, 0);

        apuestaSegura2.cancelar();

        apuestaSegura2.activar();

        bocaVsRiver.finalizar();

        assertEquals(apuestaSegura2.calcularGananciaBruta(), 141.95, 0);
        assertEquals(apuestaSegura2.calcularGananciaNeta(), 56, 1);

        assertEquals(usuario.getGananciaBruta(), 141.95, 0);
        assertEquals(usuario.getGananciaNeta(), 56, 1);

        casaDeApuestasDeportivas.setBalanceNotifier(balanceNotifierAdapter);
        casaDeApuestasDeportivas.enviarBalanceDelMesATodosLosUsuarios(5);
    }
}
