package resultadoPartido;

import algoritmoProbabilidad.AlgoritmoProbabilidad;
import oponente.Oponente;
import partido.Partido;

public class GanaLocal extends ResultadoPartido {

    @Override
    public float probabilidad(AlgoritmoProbabilidad algoritmoProbabilidad, Oponente local, Oponente visitante) {
        return algoritmoProbabilidad.probabilidadDeGanarLocal(local, visitante);
    }

    @Override
    public Oponente ganador(Partido partido) {
        return partido.getLocal();
    }


    @Override
    public ResultadoPartido getResultado(Partido partido) {
        return this;
    }

    @Override
    public String getType() {
        return "ganaLocal";
    }
}
