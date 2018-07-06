package filtroBusqueda;

public abstract class FiltroCompuesto implements Filtro {
    protected Filtro filtroIzquierda, filtroDerecha;

    public FiltroCompuesto(Filtro filtroIzquierda, Filtro filtroDerecha) {
        this.filtroIzquierda = filtroIzquierda;
        this.filtroDerecha = filtroDerecha;
    }
}
