package main;

public class MobileAppObserver implements Observer {
	
	private String ultimaNotificacao = "";
    @Override
    public void update(Transaction transaction) {
    	this.ultimaNotificacao = "📱 [Notificação Celular] Transação "+transaction.getEndereco()+" com estado de: " + transaction.getState().getStateName();
        System.out.println("📱 [Notificação Celular] Transação "+transaction.getEndereco()+" com estado de: " + transaction.getState().getStateName());
    }
    
    @Override
    public String getUltimaNotificacao() {
    	return this.ultimaNotificacao;
    }
    
    
}
