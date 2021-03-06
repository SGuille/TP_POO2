import estadoPartido.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import casaDeApuestas.CasaDeApuestasDeportivas;
import deporte.DeporteGrupal;
import oponente.Equipo;
import partido.*;
import resultadoPartido.*;
import usuario.Usuario;


public class TestPartido {

    private Partido partido1, partido2;
    private DeporteGrupal futbol;
    private List<String> posiblesResultadosFutbol;
    private LocalDateTime fechaYHora1, fechaYHora2;
    private Equipo river, boca;
    private Usuario usuario1;
    private CasaDeApuestasDeportivas casaDeApuestas1;

    @Before
    public void setUp() {

        casaDeApuestas1 = new CasaDeApuestasDeportivas();
        usuario1 = new Usuario("myUsername", "myPassword");
        boca = new Equipo("Boca");
        river = new Equipo("River");
        fechaYHora1 = LocalDateTime.of(2016,02,12,20,00,00);
        fechaYHora2 = LocalDateTime.of(2016,03,13,20,10,00);
        posiblesResultadosFutbol = new ArrayList<>();
        posiblesResultadosFutbol.add("GanaLocal");
        posiblesResultadosFutbol.add("GanaVisitante");
        posiblesResultadosFutbol.add("Empate");
        futbol = new DeporteGrupal("futbol", posiblesResultadosFutbol);
        partido1 = new Partido(futbol, fechaYHora1, "Quilmes", boca, river);
        partido2 = new Partido(futbol, fechaYHora2, "Quilmes", boca, river);
    }

    @Test
    public void testPartido1GanoBoca() {

        assertTrue(partido1.getLocal().esEquipo());
        assertTrue(partido1.getVisitante().esEquipo());
        assertTrue(partido1.noHaComenzado());
        partido1.comenzar();
        assertTrue(partido1.estaEnJuego());

        assertEquals(partido1.getLocal().getNombre(), "Boca");
        partido1.anotarGolLocal(1);
        partido1.getResultado();
        assertTrue(partido1.ganaLocal());
        assertEquals(partido1.ganador(), boca);
        assertEquals(partido1.getLugar(), "Quilmes");
        assertEquals(partido1.getDeporte(), futbol);
    }

    @Test
    public void testPartido2GanoRiver() {
        assertTrue(partido2.getLocal().esEquipo());
        assertTrue(partido2.getVisitante().esEquipo());
        assertTrue(partido2.noHaComenzado());
        partido2.comenzar();
        assertTrue(partido2.estaEnJuego());

        partido2.anotarGolVisitante(4);
        partido2.getResultado();
        assertTrue(partido2.ganaVisitante());
        assertEquals(partido2.ganador(), river);

        assertEquals(partido2.getVisitante().getNombre(), "River");
        assertEquals(partido2.getLugar(), "Quilmes");
        assertEquals(partido2.getDeporte(), futbol);
    }

    @Test
    public void testNotificarPartidoEnJuego() {
        partido1.addObserver(usuario1);
        partido1.addObserver(casaDeApuestas1);

        partido1.comenzar();

        assertTrue(partido1.estaEnJuego());
    }

    @Test
    public void testNotificarPartidoFinalizado() {
        partido2.addObserver(usuario1);

        partido2.finalizar();

        assertTrue(partido2.haFinalizado());
    }

    @Test
    public void testNotificarPartidoNoComenzado() {
        partido1.addObserver(usuario1);
        partido1.addObserver(casaDeApuestas1);

        assertTrue(partido1.noHaComenzado());
    }
}
