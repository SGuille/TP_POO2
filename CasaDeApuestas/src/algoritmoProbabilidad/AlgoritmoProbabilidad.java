package algoritmoProbabilidad;

import oponente.Oponente;
import partido.Partido;

import java.util.ArrayList;
import java.util.List;

public abstract class AlgoritmoProbabilidad {

    protected List<Partido> historial = new ArrayList();
    protected List<Partido> historialOrdenado = new ArrayList();
    protected List<Partido> ganados = new ArrayList();
    protected List<Partido> empatados = new ArrayList();

    public abstract float probabilidadDeGanarLocal(Oponente local, Oponente visitante);

    public abstract float probabilidadDeGanarVisitante(Oponente local, Oponente visitante);

    public abstract float probabilidadDeEmpatar(Oponente local, Oponente visitante);

}
