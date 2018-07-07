package oponente;

import java.util.ArrayList;

public class Equipo extends Oponente {

    private ArrayList<Deportista> integrantes;

    public Equipo(String nombre) {
        super(nombre);
        integrantes = new ArrayList<>();
    }

    @Override
    public boolean esEquipo() {
        return true;
    }

    @Override
    public boolean esDeportista() {
        return false;
    }

    public void setIntegrantes(ArrayList<Deportista> integrantes) {
        this.integrantes = integrantes;
    }

    public ArrayList<Deportista> getIntegrantes() {
        return this.integrantes;
    }
}
