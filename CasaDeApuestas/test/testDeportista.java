import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static junit.framework.TestCase.assertEquals;

import oponente.Deportista;

public class testDeportista {

    private Deportista deportista;
    private LocalDate fechaNacimiento = LocalDate.now();

    @Before
    public void setUp() {
        deportista = new Deportista("Guille", "Salvatore", fechaNacimiento, "Quilmes");
    }

    @Test
    public void testDeportista() {

        assertEquals(deportista.getNombre(), "Guille");
        assertEquals(deportista.getApellido(), "Salvatore");
        assertEquals(deportista.getFechaNacimiento(), fechaNacimiento);
        assertEquals(deportista.getLugarNacimiento(), "Quilmes");
    }

}
