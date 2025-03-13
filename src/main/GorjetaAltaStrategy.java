package main;

import java.math.BigDecimal;

public class GorjetaAltaStrategy implements GorjetaStrategy {
	
    @Override
    public BigDecimal calcularGorjeta(BigDecimal transactionAmount) {     
    	System.out.println("💰 Ajustando valor de transação com gorjeta alta...");
    	return transactionAmount.multiply(new BigDecimal("1.05"));
    }
}