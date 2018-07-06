package testCasaDeApuestasDeportivas;

import Historial.HistorialDeEnfrentamientos;
import algoritmoProbabilidad.AlgoritmoProbabilidad;
import algoritmoProbabilidad.CompetenciaHistoricaDirecta;
import algoritmoProbabilidad.HistoriaReciente;
import apuesta.ApuestaFinal;
import apuesta.ApuestaSegura;
import casaDeApuestas.*;
import deporte.DeporteGrupal;
import deporte.DeporteIndividual;
import eventoDeportivo.EventoDeportivo;
import filtroBusqueda.*;
import oponente.Equipo;
import org.junit.Before;
import org.junit.Test;
import partido.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestCasaDeApuestasDeportivas {
    private DeporteGrupal futbol;
    private DeporteIndividual tenis;
    private List<Partido> historialDePartidos;
    private EventoDeportivo eventoDeportivo1;
    private Partido bocaVsRiver1, bocaVsRiver2, bocaVsRiver3, bocaVsRiver4, riverVsBoca1, riverVsBoca2, riverVsBoca3,
                    riverVsBoca4, riverVsBoca_Empate1, riverVsBoca_Empate2;
    private Equipo boca, river;
    private HistorialDeEnfrentamientos historialDeEnfrentamientos;
    private List<String> posiblesResultadosFutbol, posiblesResultadosTenis;
    private AlgoritmoProbabilidad algoritmoProbabilidad;
    private HistoriaReciente historiaReciente;
    private CompetenciaHistoricaDirecta competenciaHistoricaDirecta;
    private CasaDeApuestasDeportivas casaDeApuestasDeportivas;
    private ApuestaFinal apuestaFinal;
    private ApuestaSegura apuestaSegura;
    private Filtro filtroLugarPartido1, filtroLugarPartido2,
                    filtroDeportePartido1, filtroDeportePartido2,
                    filtroFechaDelPartido1, filtroFechaDelPartido2,
                    filtroOponente1, filtroOponente2,
                    filtroFechaDelPartidoMenor1, filtroFechaDelPartidoMayor1;
    private FiltroCompuesto filtroAndCompuesto1, filtroAndCompuesto2, filtroOrCompuesto1, filtroOrCompuesto2,
                            filtroOrCompuestoPorAnd, filtroAndCompuestoPorOr,
                            filtroOrCompuestoPor_UnAndCompuestoPorY_unOrCompuestoPorAnd,
                            filtroAndCompuestoPor_UnAndCompuestoPorY_unOrCompuestoPorAnd;

    @Before
    public void setUp() {
        posiblesResultadosTenis = new ArrayList<>();
        posiblesResultadosTenis.add("GanaLocal");
        posiblesResultadosTenis.add("GanaVisitante");
        posiblesResultadosFutbol = new ArrayList<>();
        posiblesResultadosFutbol.add("GanaLocal");
        posiblesResultadosFutbol.add("GanaVisitante");
        posiblesResultadosFutbol.add("Empate");
        tenis = new DeporteIndividual("tenis", posiblesResultadosTenis);
        futbol = new DeporteGrupal("futbol", posiblesResultadosFutbol);
        historialDeEnfrentamientos = new HistorialDeEnfrentamientos();
        boca = new Equipo("Boca");
        river = new Equipo("River");
        historiaReciente = new HistoriaReciente();
        competenciaHistoricaDirecta = new CompetenciaHistoricaDirecta();
        casaDeApuestasDeportivas = new CasaDeApuestasDeportivas();

        // Fechas de Partidos *********************************************************************
        LocalDateTime fechaYHoraBocaVsRiver = LocalDateTime.of(2013,02,12,20,00,00);
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

        // Partidos ********************************************
        bocaVsRiver1 = new Partido(futbol,fechaYHoraBocaVsRiver1,"lugar1", boca, river);
        bocaVsRiver2 = new Partido(futbol,fechaYHoraBocaVsRiver2,"lugar2", boca, river);
        bocaVsRiver3 = new Partido(futbol,fechaYHoraBocaVsRiver3,"lugar3", boca, river);
        bocaVsRiver4 = new Partido(futbol,fechaYHoraBocaVsRiver4,"lugar4", boca, river);

        riverVsBoca1 = new Partido(futbol,fechaYHoraRiverVsBoca1,"lugar1", boca, river);
        riverVsBoca2 = new Partido(futbol,fechaYHoraRiverVsBoca2,"lugar2", boca, river);
        riverVsBoca3 = new Partido(futbol,fechaYHoraRiverVsBoca3,"lugar3", boca, river);
        riverVsBoca4 = new Partido(futbol,fechaYHoraRiverVsBoca4,"lugar4", boca, river);

        riverVsBoca_Empate1 = new Partido(futbol,fechaYHoraRiverVsBocaEmpate1,"lugar5", boca, river);
        riverVsBoca_Empate2 = new Partido(futbol,fechaYHoraRiverVsBocaEmpate2,"lugar6", boca, river);

        // Anoto Goles en los partidos para determinar el ganador luego.
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

        // Filtros *********************************************************
        filtroLugarPartido1 = new FiltroLugarPartido("lugar5");
        filtroLugarPartido2 = new FiltroLugarPartido("lugar1");
        filtroDeportePartido1 = new FiltroDeportePartido(tenis);
        filtroDeportePartido2 = new FiltroDeportePartido(futbol);
        filtroFechaDelPartido1 = new FiltroFechaDelPartido(fechaYHoraBocaVsRiver1);
        filtroFechaDelPartido2 = new FiltroFechaDelPartido(fechaYHoraRiverVsBoca1);
        filtroFechaDelPartidoMenor1 = new FiltroFechaDelPartidoMenor(fechaYHoraBocaVsRiver4);
        filtroFechaDelPartidoMayor1 = new FiltroFechaDelPartidoMayor(fechaYHoraBocaVsRiver);
        filtroOponente1 = new FiltroOponente(river);
        filtroOponente2 = new FiltroOponente(boca);

        filtroAndCompuesto1 = new FiltroAnd(filtroOponente1, filtroDeportePartido2);
        filtroOrCompuesto1 = new FiltroOr(filtroOponente2, filtroDeportePartido2);
        filtroAndCompuesto2 = new FiltroAnd(filtroOponente1, filtroDeportePartido1);
        filtroOrCompuesto2 = new FiltroOr(filtroOponente2, filtroDeportePartido1);

        filtroAndCompuestoPorOr = new FiltroAnd(filtroOrCompuesto1, filtroOrCompuesto2);
        filtroOrCompuestoPorAnd = new FiltroOr(filtroAndCompuesto1, filtroAndCompuesto2);

        filtroOrCompuestoPor_UnAndCompuestoPorY_unOrCompuestoPorAnd = new FiltroOr(filtroAndCompuestoPorOr,
                                                                                    filtroOrCompuestoPorAnd);

        filtroAndCompuestoPor_UnAndCompuestoPorY_unOrCompuestoPorAnd = new FiltroAnd(filtroAndCompuestoPorOr,
                                                                                    filtroOrCompuestoPorAnd);
        // EventoDeportivo ****************************************************
        eventoDeportivo1 = new EventoDeportivo("Superliga1", bocaVsRiver1);

        // Apuesta ************************************************************
        apuestaFinal = new ApuestaFinal(eventoDeportivo1,5);
        apuestaSegura = new ApuestaSegura(eventoDeportivo1, 5);

        // HistorialEnfrentamientos ********************************************************
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

        // CasaDeApuestas *********************************************************
        casaDeApuestasDeportivas.agregarApuesta(apuestaFinal);
        casaDeApuestasDeportivas.agregarApuesta(apuestaSegura);

    }

    @Test
    public void test1DadoUnEventoDeportivoDeFutbolDondeElHistorialDePartidosEntreEllosConstaDe20PartidosElCualBocaGano14YRiver4EntoncesLaProbabilididadQueGaneBocaEsDeUn70Porciento() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        bocaVsRiver1.anotarGolLocal(1);
        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(algoritmoProbabilidad.probabilidadDeGanarLocal(boca,river), 0.7, 0.1);

    }

    @Test
    public void test2DadoUnEventoDeportivoDeFutbolDondeElHistorialDePartidosEntreEllosConstaDe20PartidosElCualBocaGano14YRiver4EntoncesLaProbabilididadQuePierdaBocaEsDeUn20Porciento() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(algoritmoProbabilidad.probabilidadDeGanarVisitante(boca,river), 0.2, 0.1);


    }

    @Test
    public void test3DadoUnEventoDeportivoDeFutbolDondeElHistorialDePartidosEntreEllosConstaDe20PartidosElCualBocaGano14YRiver4EntoncesLaProbabilididadQueEmpateBocaEsDeUn10Porciento() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(competenciaHistoricaDirecta);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(algoritmoProbabilidad.probabilidadDeEmpatar(boca,river), 0.1, 0.1);


    }

    @Test
    public void test4DadoLosPrimerosDiezPartidosDeUnEquipoDeFutbolEnLosCualesBocaGano4EntoncesLaProbabilidadDeQueGaneElProximoPartidoEsDeUn4Porciento() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        bocaVsRiver1.anotarGolLocal(4);
        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(algoritmoProbabilidad.probabilidadDeGanarLocal(boca,river), 0.4, 0.1);


    }

    @Test
    public void test5DadoLosPrimerosDiezPartidosDeUnEquipoDeFutbolEnLosCualesBocaPerdio4EntoncesLaProbabilidadDeQuePierdaElProximoPartidoEsDeUn4Porciento() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(algoritmoProbabilidad.probabilidadDeGanarVisitante(boca,river), 0.4, 0.1);
    }

    @Test
    public void test6DadoLosPrimerosDiezPartidosDeUnEquipoDeFutbolEnLosCualesBocaEmpato2EntoncesLaProbabilidadDeQueEmpateElProximoPartidoEsDeUn2Porciento() {

        casaDeApuestasDeportivas.setAlgoritmoDeProbabilidad(historiaReciente);
        algoritmoProbabilidad = casaDeApuestasDeportivas.getAlgoritmoProbabilidad();

        assertEquals(boca.cantidadPartidosContra(river), 20);
        assertEquals(boca.cantidadPartidosGanadosPorLocalContra(river), 14);
        assertEquals(boca.cantidadPartidosEmpatadosContra(river), 2);
        assertEquals(boca.cantidadPartidosGanadosPorVisitanteContra(river), 4);

        assertEquals(algoritmoProbabilidad.probabilidadDeEmpatar(boca,river), 0.2, 0.1);


    }

    @Test
    public void test7BusquedaSimpleEventoDeportivoFiltrandoPorLugarDelPartido() {
        Set<EventoDeportivo> eventoFiltrado1 = casaDeApuestasDeportivas.buscarEventos(filtroLugarPartido1);
        Set<EventoDeportivo> eventoFiltrado2 = casaDeApuestasDeportivas.buscarEventos(filtroLugarPartido2);

        assertTrue(filtroLugarPartido2.evaluar(eventoDeportivo1));
        assertFalse(filtroLugarPartido1.evaluar(eventoDeportivo1));

        assertTrue(eventoFiltrado2.contains(eventoDeportivo1));
        assertFalse(eventoFiltrado1.contains(eventoDeportivo1));

    }

    @Test
    public void test8BusquedaSimpleEventoDeportivoFiltrandoPorDeporte() {
        Set<EventoDeportivo> eventoFiltrado1 = casaDeApuestasDeportivas.buscarEventos(filtroDeportePartido1);
        Set<EventoDeportivo> eventoFiltrado2 = casaDeApuestasDeportivas.buscarEventos(filtroDeportePartido2);

        assertTrue(filtroDeportePartido2.evaluar(eventoDeportivo1));
        assertFalse(filtroDeportePartido1.evaluar(eventoDeportivo1));

        assertTrue(eventoFiltrado2.contains(eventoDeportivo1));
        assertFalse(eventoFiltrado1.contains(eventoDeportivo1));
    }

    @Test
    public void test9BusquedaSimpleEventoDeportivoFiltrandoPorFechaDelPartido() {
        Set<EventoDeportivo> eventoFiltrado1 = casaDeApuestasDeportivas.buscarEventos(filtroFechaDelPartido1);
        Set<EventoDeportivo> eventoFiltrado2 = casaDeApuestasDeportivas.buscarEventos(filtroFechaDelPartido2);

        assertTrue(filtroFechaDelPartido1.evaluar(eventoDeportivo1));
        assertFalse(filtroFechaDelPartido2.evaluar(eventoDeportivo1));

        assertTrue(eventoFiltrado1.contains(eventoDeportivo1));
        assertFalse(eventoFiltrado2.contains(eventoDeportivo1));
    }

    @Test
    public void test10BusquedaSimpleEventoDeportivoFiltrandoPorFechaDelPartidoMayor() {
        Set<EventoDeportivo> eventoFiltrado1 = casaDeApuestasDeportivas.buscarEventos(filtroFechaDelPartidoMayor1);

        assertTrue(filtroFechaDelPartidoMayor1.evaluar(eventoDeportivo1));
        assertTrue(eventoFiltrado1.contains(eventoDeportivo1));
    }

    @Test
    public void test11BusquedaSimpleEventoDeportivoFiltrandoPorFechaDelPartidoMenor() {
        Set<EventoDeportivo> eventoFiltrado1 = casaDeApuestasDeportivas.buscarEventos(filtroFechaDelPartidoMenor1);

        assertTrue(filtroFechaDelPartidoMenor1.evaluar(eventoDeportivo1));
        assertTrue(eventoFiltrado1.contains(eventoDeportivo1));
    }

    @Test
    public void testBusquedaSimpleEventoDeportivoFiltrandoPorOponenteQueIntervieneEnElEquipo() {
        Set<EventoDeportivo> eventoFiltrado1 = casaDeApuestasDeportivas.buscarEventos(filtroOponente1);
        Set<EventoDeportivo> eventoFiltrado2 = casaDeApuestasDeportivas.buscarEventos(filtroOponente2);

        assertTrue(filtroOponente1.evaluar(eventoDeportivo1));
        assertFalse(filtroOponente2.evaluar(eventoDeportivo1));

        assertTrue(eventoFiltrado1.contains(eventoDeportivo1));
        assertFalse(eventoFiltrado2.contains(eventoDeportivo1));
    }

    @Test
    public void test12BusquedaCompuestaEventoDeportivoFiltrandoPorDeterminadoDeporteYOponente() {
        Set<EventoDeportivo> eventoFiltradoCompuesto1 = casaDeApuestasDeportivas.buscarEventos(filtroAndCompuesto1);
        Set<EventoDeportivo> eventoFiltradoCompuesto2 = casaDeApuestasDeportivas.buscarEventos(filtroOrCompuesto1);
        Set<EventoDeportivo> eventoFiltradoCompuesto3 = casaDeApuestasDeportivas.buscarEventos(filtroAndCompuesto2);
        Set<EventoDeportivo> eventoFiltradoCompuesto4 = casaDeApuestasDeportivas.buscarEventos(filtroOrCompuesto2);

        Set<EventoDeportivo> eventoFiltradoCompuesto5 = casaDeApuestasDeportivas.buscarEventos(filtroAndCompuestoPorOr);
        Set<EventoDeportivo> eventoFiltradoCompuesto6 = casaDeApuestasDeportivas.buscarEventos(filtroOrCompuestoPorAnd);

        Set<EventoDeportivo> eventoFiltradoCompuesto7 = casaDeApuestasDeportivas.buscarEventos(filtroOrCompuestoPor_UnAndCompuestoPorY_unOrCompuestoPorAnd);
        Set<EventoDeportivo> eventoFiltradoCompuesto8 = casaDeApuestasDeportivas.buscarEventos(filtroAndCompuestoPor_UnAndCompuestoPorY_unOrCompuestoPorAnd);

        assertTrue(filtroAndCompuesto1.evaluar(eventoDeportivo1));
        assertTrue(filtroOrCompuesto1.evaluar(eventoDeportivo1));
        assertTrue(filtroOrCompuestoPorAnd.evaluar(eventoDeportivo1));
        assertTrue(filtroOrCompuestoPor_UnAndCompuestoPorY_unOrCompuestoPorAnd.evaluar(eventoDeportivo1));
        assertTrue(eventoFiltradoCompuesto1.contains(eventoDeportivo1));
        assertTrue(eventoFiltradoCompuesto2.contains(eventoDeportivo1));
        assertTrue(eventoFiltradoCompuesto6.contains(eventoDeportivo1));
        assertTrue(eventoFiltradoCompuesto7.contains(eventoDeportivo1));

        assertFalse(filtroAndCompuesto2.evaluar(eventoDeportivo1));
        assertFalse(filtroOrCompuesto2.evaluar(eventoDeportivo1));
        assertFalse(filtroAndCompuestoPorOr.evaluar(eventoDeportivo1));
        assertFalse(filtroAndCompuestoPor_UnAndCompuestoPorY_unOrCompuestoPorAnd.evaluar(eventoDeportivo1));
        assertFalse(eventoFiltradoCompuesto3.contains(eventoDeportivo1));
        assertFalse(eventoFiltradoCompuesto4.contains(eventoDeportivo1));
        assertFalse(eventoFiltradoCompuesto5.contains(eventoDeportivo1));
        assertFalse(eventoFiltradoCompuesto8.contains(eventoDeportivo1));
    }
}
