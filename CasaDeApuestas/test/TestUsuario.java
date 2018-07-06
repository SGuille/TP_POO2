import org.junit.Before;
import org.junit.Test;
import usuario.Usuario;

import static junit.framework.TestCase.assertEquals;

public class TestUsuario {


    private Usuario usuario1;

    @Before
    public void setUp() {

        usuario1 = new Usuario("Guille92", "######");
        usuario1.incrementarGananciaNeta(2500);
        usuario1.incrementarGananciaBruta(2500);

    }

    @Test
    public void testUsernameUsuario() {

        assertEquals(usuario1.getUsername(), "Guille92");
    }

    @Test
    public void testPasswordUsuario() {

        assertEquals(usuario1.getPassword(), "######");
    }

    @Test
    public void testGananciaBrutaUsuario() {

        assertEquals(usuario1.getGananciaBruta(), 2500, 0);
    }

    @Test
    public void testGananciaNetaUsuario() {

        assertEquals(usuario1.getGananciaNeta(), 2500, 0);
    }


}
