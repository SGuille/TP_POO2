package usuario;

import apuesta.Apuesta;
import oponente.Oponente;
import partido.Partido;
import resultadoPartido.ResultadoPartido;

import java.util.Observable;
import java.util.Observer;

public class Usuario implements Observer {

    private String username;
    private String password;
    private double gananciaBruta;
    private double gananciaNeta;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        this.gananciaNeta = 0;
        this.gananciaBruta += this.gananciaNeta;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }

    public void incrementarGananciaNeta(double monto) {
        this.gananciaNeta += monto;
    }

    public void incrementarGananciaBruta(double monto) {
        this.gananciaBruta += monto;
    }

    public void apostar(Apuesta apuesta, int monto, ResultadoPartido resultadoAlQueApuesta, Oponente oponenteApostado) {
        apuesta.realizarApuesta(this, monto, resultadoAlQueApuesta, oponenteApostado);
    }

    public void decrementarGananciaNeta(double monto) {
        if (monto <= this.gananciaNeta) {
            this.gananciaNeta -= monto;
        } else {
            this.gananciaNeta = 0;
        }
    }

    public void decrementarGananciaBruta(double monto) {
        if (monto <= this.gananciaBruta) {
            this.gananciaBruta -= monto;
        } else {
            this.gananciaBruta = 0;
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public double getGananciaBruta() {
        return this.gananciaBruta;
    }

    public double getGananciaNeta() { return this.gananciaNeta; }

    @Override
    public void update(Observable observable, Object arg) {
        Partido partido = (Partido) observable;
        System.out.println(this + " ha sido notificado de que el " + partido.getDeporte() + " está " + arg);

    }
}
