package main;

public class ValidadorAssinaturaTransaction extends ValidadorTransaction {
    @Override
    public boolean validar(Transaction tx) {
        System.out.println("🔑 Verificando assinatura da transação...");
        if (!tx.endereco.contains("INVALIDO")) { 
            System.out.println("✅ Assinatura válida.\n");
            return super.validar(tx);
        } else {
            System.out.println("❌ Assinatura inválida. Transação rejeitada.\n");
            return false;
        }
    }
}