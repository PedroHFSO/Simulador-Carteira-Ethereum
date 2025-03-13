package main;

import java.math.BigDecimal;

public class EnvioTransaction extends Transaction {

	private String fromAddress;
	private String toAddress;
	
	public EnvioTransaction(TransactionBuilder builder) {
        super(builder);
        this.fromAddress = wallet.getEndereco();
        this.toAddress = builder.outroEndereco;
    }


    @Override
    public void executar() {
    	BigDecimal amountComTaxa = this.wallet.getGorjetaStrategy().calcularGorjeta(this.valor); 
    	if (this.wallet.getSaldo().compareTo(amountComTaxa) < 0) {
            System.out.println("❌ Saldo insuficiente para transação!");
            return;
        }    	    	
    	this.wallet.reduzirSaldo(amountComTaxa);
    	this.setState(new ConfirmedState());
    	this.wallet.inserirTransacaoHistorico(this); 
    	System.out.println("---------------");
        System.out.println("Enviando " + amountComTaxa + " ETH do " + fromAddress + " para " + toAddress);
        System.out.println("---------------\n");
    }

}
