package main;

import java.math.BigDecimal;

public class TransactionBuilder {

	protected String tipo;
    protected String outroEndereco;   
    protected BigDecimal valor;
    protected String txAddress;

    public TransactionBuilder setType(String type) {
        this.tipo = type;
        return this;
    }
    
    public TransactionBuilder setOtherAccount(String otherAccount) {
        this.outroEndereco = otherAccount;
        return this;
    }
    
    public TransactionBuilder setAmount(BigDecimal amount) {
        this.valor = amount;
        return this;
    }
    
    public TransactionBuilder setTxAddress(String txAddress) {
        this.txAddress = txAddress;
        return this;
    }
    
    public Transaction build() {
    	return TransactionFactory.criarTransacao(this);
    }
    
}
