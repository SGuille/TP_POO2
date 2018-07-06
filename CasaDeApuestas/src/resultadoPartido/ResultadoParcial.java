package resultadoPartido;

import algoritmoProbabilidad.AlgoritmoProbabilidad;
import oponente.Oponente;
import partido.Partido;

public class ResultadoParcial extends ResultadoPartido {

    @Override
    public float probabilidad(AlgoritmoProbabilidad algoritmoProbabilidad, Oponente local, Oponente visitante) {
        System.out.println("No hay resultado definido todavia");
        return 0;
    }

    @Override
    public Oponente ganador(Partido partido) {
        System.out.println("No hay un resultado definido todavia");
        return null;
    }

    @Override
    public ResultadoPartido getResultado(Partido partido) {
        return this;
    }

    @Override
    public String getType() {
        return "resultadoParcial";
    }

    @Override
    public String toString() {
        return "resultadoParcial";
    }
}
