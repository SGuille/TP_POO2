package deporte;


import oponente.Equipo;

import java.util.List;

public class DeporteGrupal extends Deporte{
	
	public DeporteGrupal(String nombre, List<String> posiblesResultados) {
		super(nombre, posiblesResultados);
		this.tipoDeOponente = new Equipo("Equipo");
	}

}
