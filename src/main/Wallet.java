package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Wallet implements Subject{
	private static Wallet instance;
	protected BigDecimal saldo;
    protected String enderecoConta;
    protected List<Transaction> historicoTransaction;
    private List<Observer> observers = new ArrayList<>();
    protected ValidadorTransaction listaValidacao;
    protected GorjetaStrategy gorjetaStrategy;

    protected Wallet(String endereco) { 
    	this.saldo = BigDecimal.ZERO;
    	this.enderecoConta = endereco;
    	this.historicoTransaction = new ArrayList<Transaction>();
    	this.gorjetaStrategy = new GorjetaBaixaStrategy(); //configuração padrão
    	this.configurarValidacao();
    }

    public static Wallet getInstance() {
        return instance;
    }
    
    public static void setInstance(Wallet newInstance) {
        if (instance == null) {
            instance = newInstance;
        } else {
            System.out.println("❌ Carteira já foi criada!");
        }
    }
    
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Transaction transaction) {
        for (Observer observer : observers) {
            observer.update(transaction);
        }
    }
    
    public List<Observer> getObservers() {
    	return this.observers;
    }
    
    protected abstract void configurarValidacao();
    
    public abstract void processTransaction(Transaction tx);

    public void inserirTransacaoHistorico(Transaction tx) {
    	historicoTransaction.add(tx);
    }
    
    public void reduzirSaldo(BigDecimal val) {
    	this.saldo = saldo.subtract(val);
    }
    
    public void aumentarSaldo(BigDecimal val) {
    	this.saldo = this.saldo.add(val);
    }
    
    public void mostrarHistoricoTransaction() {
        System.out.println("\n📜 Transaction History:");
        for (Transaction tx : historicoTransaction) {
            System.out.println("- "+tx.getEndereco()+"\t"+tx.getState().getStateName());
        }
    }
    
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public GorjetaStrategy getGorjetaStrategy() {
		return this.gorjetaStrategy;
	}
	
	public void setGorjetaStrategy(GorjetaStrategy strategy) {
		this.gorjetaStrategy = strategy;
	}

	public String getEndereco() {
		return enderecoConta;
	}
	
	public Transaction getUltimaTransacao() {
		return this.historicoTransaction.getLast();
	}
	
	public ValidadorTransaction getListaValidacao() {
		return this.listaValidacao;
	}



}