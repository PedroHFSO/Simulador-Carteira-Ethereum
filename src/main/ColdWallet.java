package main;

import java.math.BigDecimal;

class ColdWallet extends Wallet {
	
    public ColdWallet(String endereco) {
        super(endereco);
        System.out.println("❄️ Hot Wallet criada! (Armazenamento Offline)\n");
    }
    
    
    @Override
    public void processTransaction(Transaction tx){                     
        if(!this.listaValidacao.validar(tx)) {
        	tx.setState(new FailedState());
            this.inserirTransacaoHistorico(tx);
        	return;
        }
        tx.executar();               
        System.out.println("✅ Novo Saldo: " + this.saldo + " ETH\n-------------------------");
    }

	@Override
	protected void configurarValidacao() {
        ValidadorTransaction validaAssinatura = new ValidadorAssinaturaTransaction();
        ValidadorTransaction validaVal = new ValidadorValorTransaction();
        ValidadorTransaction validarManual = new ValidadorManualTransaction();
        validaAssinatura.setProximo(validaVal);
        validaVal.setProximo(validarManual);
        this.listaValidacao = validaAssinatura;
    }
}