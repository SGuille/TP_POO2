package resultadoPartido;

import algoritmoProbabilidad.AlgoritmoProbabilidad;
import oponente.Oponente;
import partido.Partido;

public class Empate extends ResultadoPartido {

    @Override
    public float probabilidad(AlgoritmoProbabilidad algoritmoProbabilidad, Oponente local, Oponente visitante) {
        return algoritmoProbabilidad.probabilidadDeEmpatar(local, visitante);
    }

    @Override
    public Oponente ganador(Partido partido) {
        if (partido.getDeporte().admiteEmpate()) {
            System.out.println("Empate");
        }else {
            System.out.println("No hay ganador definido porque el: " + partido.getDeporte() + " no admite un empate");
        }
        return null;
    }


    @Override
    public ResultadoPartido getResultado(Partido partido) {
        if (partido.getDeporte().admiteEmpate()) {
            return this;
        }else {
            System.out.println("No hay ganador definido porque el: " + partido.getDeporte() + " no admite un empate");
            return new ResultadoParcial();
        }
    }

    @Override
    public String getType() {
        return "empate";
    }
}
