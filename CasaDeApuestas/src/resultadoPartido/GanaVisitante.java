package resultadoPartido;

import algoritmoProbabilidad.AlgoritmoProbabilidad;
import oponente.Oponente;
import partido.Partido;

public class GanaVisitante extends ResultadoPartido {
    @Override
    public float probabilidad(AlgoritmoProbabilidad algoritmoProbabilidad, Oponente local, Oponente visitante) {
        return algoritmoProbabilidad.probabilidadDeGanarVisitante(local, visitante);
    }

    @Override
    public Oponente ganador(Partido partido) {
        return partido.getVisitante();
    }

    @Override
    public ResultadoPartido getResultado(Partido partido) {
        return this;
    }

    @Override
    public String getType() {
        return "ganaVisitante";
    }
}
