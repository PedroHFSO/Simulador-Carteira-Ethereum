package main;

import java.math.BigDecimal;

public class CriarContratoInteligenteTransaction extends Transaction {

    
	private String enderecoContrato;
	private String enderecoOrigem;
	
    public CriarContratoInteligenteTransaction(TransactionBuilder builder) {
        super(builder);
        this.enderecoContrato = builder.outroEndereco;
        this.enderecoOrigem = wallet.getEndereco();
    }


    @Override
    public void executar() {
    	System.out.println("---------------");
        System.out.println("Fazendo deploy de contrato inteligente...");
        System.out.println("Do: " + enderecoOrigem);
        System.out.println("✅ Deploy do Contrato Inteligente em endereço: "+ enderecoContrato);
        System.out.println("---------------\n");
        this.setState(new ConfirmedState());
        this.wallet.inserirTransacaoHistorico(this); 
    }

}
