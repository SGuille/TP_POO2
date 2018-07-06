package deporte;

import oponente.Deportista;

import java.time.LocalDate;
import java.util.List;

public class DeporteIndividual extends Deporte{

	public DeporteIndividual(String nombre, List<String> posiblesResultados) {
		super(nombre, posiblesResultados);
		this.tipoDeOponente = new Deportista("nombre", "apellido", LocalDate.now(), "lugar");
	}

}
