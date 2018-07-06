package testEventoDeportivo;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import Historial.HistorialDeEnfrentamientos;
import algoritmoProbabilidad.AlgoritmoProbabilidad;
import algoritmoProbabilidad.CompetenciaHistoricaDirecta;
import algoritmoProbabilidad.HistoriaReciente;
import apuesta.ApuestaFinal;
import casaDeApuestas.CasaDeApuestasDeportivas;
import deporte.DeporteGrupal;
import eventoDeportivo.EventoDeportivo;
import oponente.Equipo;
import partido.*;

public class TestEventoDeportivo {

    private DeporteGrupal futbol;
    private List<Partido> historialDePartidos;
    private EventoDeportivo eventoDeportivo1, eventoDeportivo2, eventoDeportivo3;
    private Partido bocaVsRiver1, bocaVsRiver2, bocaVsRiver3, bocaVsRiver4, riverVsBoca1, riverVsBoca2, riverVsBoca3,
                    riverVsBoca4, riverVsBoca_Empate1, riverVsBoca_Empate2;
    private Equipo boca, river;
    private HistorialDeEnfrentamientos historialDeEnfrentamientos;
    private List<String> posiblesResultadosFutbol;
    private HistoriaReciente historiaReciente;
    private CompetenciaHistoricaDirecta competenciaHistoricaDirecta;
    private AlgoritmoProbabilidad algoritmoProbabilidad;
    private CasaDeApuestasDeportivas casaDeApuestasDeportivas;
    private ApuestaFinal apuestaFinal;

    @Before
	public void setUp() {

        posiblesResultadosFutbol = new ArrayList<>();
        posiblesResultadosFutbol.add("GanaLocal");
        posiblesResultadosFutbol.add("GanaVisitante");
        posiblesResultadosFutbol.add("Empate");
        futbol = new DeporteGrupal("futbol", posiblesResultadosFutbol);
        historialDeEnfrentamientos = new HistorialDeEnfrentamientos();
        boca = new Equipo("Boca");
        river = new Equipo("River");
        historiaReciente = new HistoriaReciente();
        competenciaHistoricaDirecta = new CompetenciaHistoricaDirecta();
        casaDeApuestasDeportivas = new CasaDeApuestasDeportivas();

        LocalDateTime fechaYHoraBocaVsRiver1 = LocalDateTime.of(2016,02,12,20,00,00);
        LocalDateTime fechaYHoraBocaVsRiver2 = LocalDateTime.of(2016,03,13,20,10,00);
        LocalDateTime fechaYHoraBocaVsRiver3 = LocalDateTime.of(2017,02,14,21,00,15);
        LocalDateTime fechaYHoraBocaVsRiver4 = LocalDateTime.of(2018,05,14,21,00,15);

        LocalDateTime fechaYHoraRiverVsBoca1 = LocalDateTime.of(2016,06,3,20,00,15);
        LocalDateTime fechaYHoraRiverVsBoca2 = LocalDateTime.of(2017,05,3,20,00,15);
        LocalDateTime fechaYHoraRiverVsBoca3 = LocalDateTime.of(2016,03,3,20,00,15);
        LocalDateTime fechaYHoraRiverVsBoca4 = LocalDateTime.of(2017,03,3,20,00,15);

        LocalDateTime fechaYHoraRiverVsBocaEmpate1 = LocalDateTime.of(2016,10,20,19,00,15);
        LocalDateTime fechaYHoraRiverVsBocaEmpate2 = LocalDateTime.of(2017,01,20,19,00,15);

        bocaVsRiver1 = new Partido(futbol,fechaYHoraBocaVsRiver1,"bocaVsRiver1", boca, river);
        bocaVsRiver2 = new Partido(futbol,fechaYHoraBocaVsRiver2,"bocaVsRiver2", boca, river);
        bocaVsRiver3 = new Partido(futbol,fechaYHoraBocaVsRiver3,"BocaVsRiver3", boca, river);
        bocaVsRiver4 = new Partido(futbol,fechaYHoraBocaVsRiver4,"BocaVsRiver4", boca, river);

        riverVsBoca1 = new Partido(futbol,fechaYHoraRiverVsBoca1,"riverVsBoca1", boca, river);
        riverVsBoca2 = new Partido(futbol,fechaYHoraRiverVsBoca2,"riverVsBoca2", boca, river);
        riverVsBoca3 = new Partido(futbol,fechaYHoraRiverVsBoca3,"riverVsBoca3", boca, river);
        riverVsBoca4 = new Partido(futbol,fechaYHoraRiverVsBoca4,"riverVsBoca4", boca, river);

        riverVsBoca_Empate1 = new Partido(futbol,fechaYHoraRiverVsBocaEmpate1,"riverVsBoca_Empate1", boca, river);
        riverVsBoca_Empate2 = new Partido(futbol,fechaYHoraRiverVsBocaEmpate2,"riverVsBoca_Empate2", boca, river);

        bocaVsRiver1.anotarGolLocal(2);
        bocaVsRiver2.anotarGolLocal(1);
        bocaVsRiver3.anotarGolLocal(3);
        bocaVsRiver4.anotarGolLocal(2);

        riverVsBoca1.anotarGolVisitante(2);
        riverVsBoca2.anotarGolVisitante(2);
        riverVsBoca3.anotarGolVisitante(2);
        riverVsBoca4.anotarGolVisitante(2);

        riverVsBoca_Empate1.anotarGolLocal(1);
        riverVsBoca_Empate1.anotarGolVisitante(1);

        riverVsBoca_Empate2.anotarGolLocal(1);
        riverVsBoca_Empate2.anotarGolVisitante(1);

        eventoDeportivo1 = new EventoDeportivo("Superliga1", bocaVsRiver1);
        eventoDeportivo2 = new EventoDeportivo("Superliga3", riverVsBoca1);
        eventoDeportivo3 = new EventoDeportivo("Superliga4", riverVsBoca_Empate1);

        apuestaFinal = new ApuestaFinal(eventoDeportivo1, 5);

        casaDeApuestasDeportivas.agregarApuesta(apuestaFinal);

        historialDePartidos = new ArrayList<>();

        historialDePartidos.add(bocaVsRiver1);
        historialDePartidos.add(bocaVsRiver2);
        historialDePartidos.add(bocaVsRiver3);
        historialDePartidos.add(bocaVsRiver2);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);
        historialDePartidos.add(bocaVsRiver4);

        historialDePartidos.add(riverVsBoca1);
        historialDePartidos.add(riverVsBoca2);
        historialDePartidos.add(riverVsBoca3);
        historialDePartidos.add(riverVsBoca4);

        historialDePartidos.add(riverVsBoca_Empate1);
        historialDePartidos.add(riverVsBoca_Empate2);

        historialDeEnfrentamientos.setHistorial(historialDePartidos);

        boca.setHistorialDeEnfrentamientos(historialDeEnfrentamientos);
        river.setHistorialDeEnfrentamientos(historialDeEnfrentamientos);
    }

	@Test
	public void testDadoUnEventoDeportivoDeFutbolDondeElHistorialDePartidosEntreEllosConstaDe20PartidosElCualBocaGano14YRiver4CalculoCuota1() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        bocaVsRiver1.anotarGolLocal(2);
        bocaVsRiver2.anotarGolLocal(1);
        bocaVsRiver3.anotarGolLocal(3);
        bocaVsRiver4.anotarGolLocal(2);

        riverVsBoca1.anotarGolVisitante(2);
        riverVsBoca2.anotarGolVisitante(2);
        riverVsBoca3.anotarGolVisitante(2);
        riverVsBoca4.anotarGolVisitante(2);

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);
        assertEquals(algoritmoProbabilidad.probabilidadDeGanarLocal(boca,river), 0.7, 0.1);
        assertEquals(eventoDeportivo1.calcularCuota(boca, algoritmoProbabilidad), 1.3, 0.1);
        assertEquals(eventoDeportivo1.getNombre(), "Superliga1");
        assertEquals(eventoDeportivo1.getPartido(), bocaVsRiver1);
	}

	@Test
	public void testDadoUnEventoDeportivoDeFutbolDondeElHistorialDePartidosEntreEllosConstaDe20PartidosElCualBocaGano14YRiver4CalculoCuota2() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();


        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(eventoDeportivo2.calcularCuota(boca, algoritmoProbabilidad), 1.8, 0.1);
        assertEquals(eventoDeportivo2.getNombre(), "Superliga3");
        assertEquals(eventoDeportivo2.getPartido(), riverVsBoca1);
	}

	@Test
	public void testDadoUnEventoDeportivoDeFutbolDondeElHistorialDePartidosEntreEllosConstaDe20PartidosElCualBocaGano14YRiver4CalculoCuota3() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(eventoDeportivo3.calcularCuota(boca, algoritmoProbabilidad), 1.9, 0.1);
        assertEquals(eventoDeportivo3.getNombre(), "Superliga4");
        assertEquals(eventoDeportivo3.getPartido(), riverVsBoca_Empate1);
	}

	@Test
	public void testDadoLosPrimerosDiezPartidosDeUnEquipoDeFutbolEnLosCualesBocaGano4CalculoCuota4() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(eventoDeportivo1.calcularCuota(boca, algoritmoProbabilidad), 1.6, 0.1);
        assertEquals(eventoDeportivo1.getNombre(), "Superliga1");
        assertEquals(eventoDeportivo1.getPartido(), bocaVsRiver1);
	}

	@Test
	public void testDadoLosPrimerosDiezPartidosDeUnEquipoDeFutbolEnLosCualesBocaPerdio4CalculoCuota5() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(eventoDeportivo2.calcularCuota(boca, algoritmoProbabilidad), 1.6, 0.1);
        assertEquals(eventoDeportivo2.getNombre(), "Superliga3");
        assertEquals(eventoDeportivo2.getPartido(), riverVsBoca1);
	}

	@Test
	public void testDadoLosPrimerosDiezPartidosDeUnEquipoDeFutbolEnLosCualesBocaEmpato2CalculoCuota6() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(eventoDeportivo3.calcularCuota(boca, algoritmoProbabilidad), 1.8, 0.1);
        assertEquals(eventoDeportivo3.getNombre(), "Superliga4");
        assertEquals(eventoDeportivo3.getPartido(), riverVsBoca_Empate1);
	}
}
