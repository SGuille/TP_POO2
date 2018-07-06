package balanceNotifier;

import usuario.Usuario;

import java.math.BigDecimal;


public interface BalanceNotifier {

    void notifyBalance(Usuario user, Integer month, BigDecimal monthlyBalance);
}
