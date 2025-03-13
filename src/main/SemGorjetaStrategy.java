package main;

import java.math.BigDecimal;

public class SemGorjetaStrategy implements GorjetaStrategy {
	
    @Override
    public BigDecimal calcularGorjeta(BigDecimal transactionAmount) {
        return transactionAmount;
    	//return transactionAmount.multiply(new BigDecimal("1.01"));
    }
}