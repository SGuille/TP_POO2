package oponente;

import java.time.LocalDate;

public class Deportista extends Oponente {

	private String apellido;
	private LocalDate fechaNacimiento;
	private String lugarNacimiento;

	public Deportista(String nombre, String apellido, LocalDate fechaNacimiento, String lugarNacimiento){

		super(nombre);
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getLugarNacimiento() {
		return this.lugarNacimiento;
	}

    @Override
    public boolean esEquipo() {
        return false;
    }

    @Override
    public boolean esDeportista() {
        return true;
    }
}
