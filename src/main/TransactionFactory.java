package main;

import java.math.BigDecimal;

public class TransactionFactory {
    public static Transaction criarTransacao(TransactionBuilder builder) {
        switch (builder.tipo.toLowerCase()) {
            case "envio":
                return new EnvioTransaction(builder);
            case "receber":
                return new ReceberTransaction(builder);
            case "criar_contrato":
                return new CriarContratoInteligenteTransaction(builder);
            default:
                throw new IllegalArgumentException("Tipo de transação não implementado: " + builder.tipo);
        }
    }
}
