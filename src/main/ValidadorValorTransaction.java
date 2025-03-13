package main;

import java.math.BigDecimal;

public class ValidadorValorTransaction extends ValidadorTransaction {
    @Override
    public boolean validar(Transaction tx) {
        System.out.println("🔑 Verificando valor da transação...");
        if (tx.valor.compareTo(BigDecimal.ZERO) >= 0) {
            System.out.println("✅ Transação com valor válido.\n");
            return super.validar(tx);
        } else {
            System.out.println("❌ Valor de transação não pode ser negativo. Transação rejeitada.\n");
            return false;
        }
    }
}