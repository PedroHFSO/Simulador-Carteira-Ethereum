package main;

import java.math.BigDecimal;

public class GorjetaBaixaStrategy implements GorjetaStrategy {
	
    @Override
    public BigDecimal calcularGorjeta(BigDecimal transactionAmount) {       
    	System.out.println("💰 Ajustando valor de transação com gorjeta baixa...");
    	return transactionAmount.multiply(new BigDecimal("1.01"));
    }
}