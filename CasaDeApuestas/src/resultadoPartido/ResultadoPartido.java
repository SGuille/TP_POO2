package resultadoPartido;

import algoritmoProbabilidad.AlgoritmoProbabilidad;
import oponente.Oponente;
import partido.Partido;

public abstract class ResultadoPartido {

    public abstract float probabilidad(AlgoritmoProbabilidad algoritmoProbabilidad, Oponente local, Oponente visitante);

    public abstract Oponente ganador(Partido partido);
    public abstract ResultadoPartido getResultado(Partido partido);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public abstract String getType();
}
