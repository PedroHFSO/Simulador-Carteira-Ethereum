package main;

import java.math.BigDecimal;

public abstract class Transaction {
    protected Wallet wallet;
    protected String outraConta;
    protected BigDecimal valor;
    protected String endereco;
    protected TransactionState state;
    
    protected Transaction(TransactionBuilder builder) {
    	this.wallet = Wallet.getInstance();
        this.outraConta = builder.outroEndereco;
        this.valor = builder.valor;
        this.endereco = builder.txAddress;
        this.state = new PendingState();
    }

    public abstract void executar();

	public String getEndereco() {
		return endereco;
	}
	
	public void setState(TransactionState state) {
        this.state = state;
        Wallet.getInstance().notifyObservers(this);
    }

    public TransactionState getState() {
        return state;
    }
}
