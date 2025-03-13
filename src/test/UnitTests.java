package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

    
    @BeforeEach
    void setUp() { //Criação do Singleton da Wallet
    	WalletFactory walletFactory = new HotWalletFactory();
    	Wallet wallet = walletFactory.getWallet();
    }
    
    
    @Test //Teste Singleton
    void testeCriacaoWallet() {    	
    	WalletFactory outraWalletFactory = new ColdWalletFactory();
    	Wallet wallet2 = outraWalletFactory.getWallet();
    	
    	assertEquals("0xEnderecoHotWallet", wallet2.getEndereco()); //Carteira não pode ser redefinida, já foi criada no setUp()
    }
    
    //Testes AbstractFactory, FactoryMethod, TemplateMethod
    
    @Test 
    void testeTransactionFactoryEnvio() {
    	String tipo = "envio";
    	Transaction tx = TransactionFactory.criarTransacao(new TransactionBuilder()
    			.setAmount(BigDecimal.ONE)
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt")
    			.setType(tipo)
    	);
    	assertEquals("0xt", tx.getEndereco());
    }
    
    @Test
    void testeTransactionFactoryInvalido() {
    	String tipo = "nao_existe";
    	Exception e = assertThrows(IllegalArgumentException.class, () ->
    	TransactionFactory.criarTransacao(new TransactionBuilder()
    			.setAmount(BigDecimal.ONE)
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt")
    			.setType(tipo)
    	));
    	assertEquals("Tipo de transação não implementado: nao_existe", e.getMessage());
    }

    //Testes State e Observer
    
    @Test
    void testeTransactionNegativa() {
    	Wallet.getInstance().processTransaction(new TransactionBuilder()
    			.setAmount(new BigDecimal(-1))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt")
    			.setType("receber")
    			.build()
    	);
    	
    	assertEquals("rejeitada", Wallet.getInstance().getUltimaTransacao().getState().getStateName());
    }
    
    @Test
    void testeTransactionAssinaturaInvalida() {
    	Wallet.getInstance().processTransaction(new TransactionBuilder()
    			.setAmount(new BigDecimal(10))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xtINVALIDO")
    			.setType("receber")
    			.build()
    	);
    	
    	assertEquals("rejeitada", Wallet.getInstance().getUltimaTransacao().getState().getStateName());
    }
    
    @Test
    void testeTransactionValida() {
    	Wallet.getInstance().processTransaction(new TransactionBuilder()
    			.setAmount(new BigDecimal(10))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt")
    			.setType("receber")
    			.build()
    	);
    	
    	assertEquals("confirmada", Wallet.getInstance().getUltimaTransacao().getState().getStateName());
    }
    
    @Test
    void testeTransactionPendente() {
    	Transaction tx = new TransactionBuilder()
    			.setAmount(new BigDecimal(10))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt")
    			.setType("receber")
    			.build(); //não foi processada, estado não alterou
        	
    	assertEquals("pendente", tx.getState().getStateName());
    }
    
    @Test
    void testeNotifyConfirm() {
    	Wallet.getInstance().processTransaction(new TransactionBuilder()
    			.setAmount(new BigDecimal(10))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt")
    			.setType("receber")
    			.build()
    	);
    	for(Observer o: Wallet.getInstance().getObservers()) {
    		assertNotEquals("", o.getUltimaNotificacao());
    	}
    }
    
    @Test
    void testeSemNotify() {    	
    	for(Observer o: Wallet.getInstance().getObservers()) {
    		assertEquals("", o.getUltimaNotificacao());
    	}
    }
    
    //Testes Strategy
    
    @Test
    void testeStrategyGorjetaAlta() {
    	Wallet.getInstance().reduzirSaldo(Wallet.getInstance().getSaldo()); //resetar saldo para 0
    	Wallet.getInstance().setGorjetaStrategy(new GorjetaAltaStrategy());
    	Wallet.getInstance().processTransaction(new TransactionBuilder()
    			.setAmount(new BigDecimal(10))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt")
    			.setType("receber")
    			.build()
    	);
    	
    	Wallet.getInstance().processTransaction(new TransactionBuilder()
    			.setAmount(new BigDecimal(2))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt2")
    			.setType("envio")
    			.build()
    	);
    	
    	assertEquals("7.90", Wallet.getInstance().getSaldo().toString());
    	
    }
    
    //Testes ChainOfResponsability
    
    @Test
    void testeValidacaoValor() {
    	Transaction tx = new TransactionBuilder()
    			.setAmount(new BigDecimal(-1))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xt")
    			.setType("receber")
    			.build();
    	ValidadorTransaction correnteValidacao = Wallet.getInstance().getListaValidacao();
    	assertFalse(correnteValidacao.validar(tx));
    }
    
    @Test
    void testeValidacaoAssinatura() {
    	Transaction tx = new TransactionBuilder()
    			.setAmount(new BigDecimal(1))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xINVALIDO")
    			.setType("receber")
    			.build();    	
    	ValidadorTransaction correnteValidacao = Wallet.getInstance().getListaValidacao();
    	assertFalse(correnteValidacao.validar(tx));
    }
    
    @Test
    void testeValidacaoValida() {
    	Transaction tx = new TransactionBuilder()
    			.setAmount(new BigDecimal(1))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xVALIDO")
    			.setType("receber")
    			.build();    	
    	ValidadorTransaction correnteValidacao = Wallet.getInstance().getListaValidacao();
    	assertTrue(correnteValidacao.validar(tx));
    }
    
    //Teste Builder
    
    @Test
    void testeCriacaoTransactionBuilder() {
    	Transaction tx = new TransactionBuilder()
    			.setAmount(new BigDecimal(1))
    			.setOtherAccount("0xa")
    			.setTxAddress("0xVALIDO")
    			.setType("receber")
    			.build();    	
    	assertEquals("0xVALIDO", tx.getEndereco());
    }
}
