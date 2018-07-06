package balanceNotifier;

import java.math.BigDecimal;

public class EmailBalanceNotifier {

    void emailBalance(String userName, Integer month, BigDecimal monthlyBalance) {
        System.out.println("\n notificar balance mensual: " + monthlyBalance + " de mes "+ month +" a usuario: " + userName + " \n");
    }
}
