package algoritmoProbabilidad;

import oponente.Oponente;

public abstract class AlgoritmoProbabilidad {

    public abstract float probabilidadDeGanarLocal(Oponente local, Oponente visitante);

    public abstract float probabilidadDeGanarVisitante(Oponente local, Oponente visitante);

    public abstract float probabilidadDeEmpatar(Oponente local, Oponente visitante);

}
