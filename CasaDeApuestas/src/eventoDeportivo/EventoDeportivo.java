package eventoDeportivo;

import algoritmoProbabilidad.AlgoritmoProbabilidad;
import oponente.Oponente;
import partido.Partido;

public class EventoDeportivo {

    private String nombre;
    private Partido partido;

	public EventoDeportivo(String nombre, Partido partido) {
        this.nombre = nombre;
        this.partido = partido;
	}

    public String getNombre() {
        return nombre;
    }
    public Partido getPartido() {
		return partido;
	}

	public double calcularCuota(Oponente oponente, AlgoritmoProbabilidad algoritmoProbabilidad){

        Oponente local = partido.getLocal().equals(oponente) ? oponente : this.partido.getLocal();
        Oponente visitante = partido.getVisitante().equals(oponente) ? oponente : this.partido.getVisitante();

        float probabilidad = partido.probabilidad(algoritmoProbabilidad, local, visitante);

        System.out.print("\nprobabilidad: " + probabilidad + " | " +
                            "local: " + local + " | " + "visitante: " + visitante + " | " +
                            "cuota: " + (1 + (1 - probabilidad)) + " | " +
                            "resultado: " + partido.getResultado() + " | \n");

        return 1 + (1 - probabilidad);
    }
}
