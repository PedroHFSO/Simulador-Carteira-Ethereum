package main;

public class ReceberTransaction extends Transaction {

	private String toAddress;
	private String fromAddress;
	
	public ReceberTransaction(TransactionBuilder builder) {
        super(builder);
        this.toAddress = wallet.getEndereco();
        this.fromAddress = builder.outroEndereco;
    }

    @Override
    public void executar() {
    	System.out.println("---------------");
        System.out.println("Recebendo " + this.valor + " ETH do " + fromAddress + " para " + toAddress);
        System.out.println("---------------\n");
        this.wallet.aumentarSaldo(this.valor);
        this.setState(new ConfirmedState());
    	this.wallet.inserirTransacaoHistorico(this); 
    }

}
