package algoritmoProbabilidad;

import oponente.Oponente;

public class CompetenciaHistoricaDirecta extends AlgoritmoProbabilidad {

    public float probabilidadDeGanarLocal(Oponente local, Oponente visitante) {

        Integer sizeHistorial = local.cantidadPartidosContra(visitante);
        Integer sizeGanados = local.cantidadPartidosGanadosPorLocalContra(visitante);

        return sizeGanados / (float)sizeHistorial;
    }

    public float probabilidadDeGanarVisitante(Oponente local, Oponente visitante) {

        Integer sizeHistorial = local.cantidadPartidosContra(visitante);
        Integer sizeGanadosPorVisitante = local.cantidadPartidosGanadosPorVisitanteContra(visitante);

        return sizeGanadosPorVisitante / (float)sizeHistorial;
    }

    public float probabilidadDeEmpatar(Oponente local, Oponente visitante) {

        Integer sizeHistorial = local.cantidadPartidosContra(visitante);
        Integer sizeEmpatados = local.cantidadPartidosEmpatadosContra(visitante);

        return sizeEmpatados / (float)sizeHistorial;
    }

}
