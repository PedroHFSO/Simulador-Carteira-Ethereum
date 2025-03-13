package main;

import java.math.BigDecimal;

class HotWallet extends Wallet {
    public HotWallet(String endereco) {
        super(endereco);
        System.out.println("🔥 Hot Wallet criada! (Conectado na rede)\n");
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
        validaAssinatura.setProximo(validaVal);
        this.listaValidacao = validaAssinatura;
    }
}