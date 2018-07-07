import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import deporte.DeporteGrupal;
import deporte.DeporteIndividual;
import oponente.Deportista;
import oponente.Equipo;

public class TestDeporte {

    private DeporteGrupal futbol;
    private DeporteIndividual tenis;
    private List<String> posiblesResultadosFutbol, posiblesResultadosTenis;

    @Before
    public void setUp() {

        posiblesResultadosTenis = new ArrayList<>();
        posiblesResultadosTenis.add("GanaLocal");
        posiblesResultadosTenis.add("GanaVisitante");

        posiblesResultadosFutbol = new ArrayList<>();
        posiblesResultadosFutbol.add("GanaLocal");
        posiblesResultadosFutbol.add("GanaVisitante");
        posiblesResultadosFutbol.add("Empate");
        futbol = new DeporteGrupal("futbol", posiblesResultadosFutbol);
        tenis = new DeporteIndividual("tenis", posiblesResultadosTenis);
    }

    @Test
    public void testDeporte() {

        assertTrue(futbol.esUnDeporteGrupal());
        assertTrue(tenis.esUnDeporteIndividual());

        assertFalse(tenis.posiblesResultados().contains("Empate"));

        assertTrue(tenis.posiblesResultados().contains("GanaVisitante"));
        assertTrue(tenis.posiblesResultados().contains("GanaLocal"));

        assertTrue(futbol.posiblesResultados().contains("GanaLocal"));
        assertTrue(futbol.posiblesResultados().contains("GanaVisitante"));
        assertTrue(futbol.posiblesResultados().contains("Empate"));

        assertEquals(tenis.getNombre(), "tenis");
        assertEquals(futbol.getNombre(), "futbol");

        assertTrue(futbol.getTipoDeOponente().esEquipo());
        assertTrue(tenis.getTipoDeOponente().esDeportista());
    }
}
