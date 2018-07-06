package balanceNotifier;

import usuario.Usuario;

import java.math.BigDecimal;

public class BalanceNotifierAdapter implements BalanceNotifier {

    private EmailBalanceNotifier email;

    public BalanceNotifierAdapter(EmailBalanceNotifier email) {
        this.email = email;
    }

    @Override
    public void notifyBalance(Usuario user, Integer month, BigDecimal monthlyBalance) {
        email.emailBalance(user.getUsername(), month, monthlyBalance);
    }
}
