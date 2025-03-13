package main;

public interface Observer {	
	public String getUltimaNotificacao();
    void update(Transaction transaction);
}