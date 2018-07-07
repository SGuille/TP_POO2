package deporte;

import oponente.Oponente;

import java.util.List;

public abstract class Deporte {

	private String nombre;
	Oponente tipoDeOponente;
	private List<String> posiblesResultados;

	public Deporte(String nombre, List<String> posiblesResultados){

		this.nombre = nombre;
		this.posiblesResultados = posiblesResultados;
	}

	@Override
    public String toString() {
	    return this.getNombre();
    }

	public String getNombre(){
		return nombre;
	}

	public Oponente getTipoDeOponente() {
		return this.tipoDeOponente;
	}

	public List<String> posiblesResultados() {
	    return this.posiblesResultados;
    }

    public boolean admiteEmpate() {
	    return posiblesResultados().contains("Empate");
    }

    public abstract boolean esUnDeporteGrupal();

    public abstract boolean esUnDeporteIndividual();
}
