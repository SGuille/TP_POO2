package balanceNotifier;

import usuario.Usuario;

import java.math.BigDecimal;

public class TextMessageBalanceNotifier implements BalanceNotifier {

    @Override
    public void notifyBalance(Usuario user, Integer month, BigDecimal monthlyBalance)
    {
      System.out.println("\n notificar balance mensual: " + monthlyBalance + " a usuario: " + user + " \n");
    }
}
