package main;

class LoggerObserver implements Observer {
	
	private String ultimaNotificacao;
	
    @Override
    public void update(Transaction transaction) {
    	this.ultimaNotificacao = "📝 [Log] Estado de transação "+transaction.getEndereco()+" alterado para: " + transaction.getState().getStateName();
        System.out.println("📝 [Log] Estado de transação "+transaction.getEndereco()+" alterado para: " + transaction.getState().getStateName());
    }
    
    @Override
    public String getUltimaNotificacao() {
    	return this.ultimaNotificacao;
    }
    
    
}
