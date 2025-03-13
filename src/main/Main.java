package main;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
	    System.out.println("Escolha o tipo de carteira (Hot Wallet - 1) ou (Cold Wallet - 2)");
	    String walletType = ""; 
		do {
			walletType = scan.nextLine(); 	
		}while(!walletType.equals("1") && !walletType.equals("2"));			    		    
		
	    WalletFactory factory;
	    
		if(walletType.equals("1")) {
	    	factory = new HotWalletFactory();
	    }else {
	    	factory = new ColdWalletFactory();
	    }
		
		Wallet wallet = factory.getWallet();
		
		System.out.println("Escolha a estratégia para gorjetas: (Não dar gorjetas - 0), (Gorjetas baixas (1%) - 1), (Gorjetas altas (5%) - 2)");
		System.out.println("Pressione qualquer outra coisa para manter no padrão (Gorjetas baixas)");
	    String tip = scan.nextLine();
	    
	    if(tip.equals("0")) wallet.setGorjetaStrategy(new SemGorjetaStrategy());
	    if(tip.equals("1")) wallet.setGorjetaStrategy(new GorjetaBaixaStrategy());
	    if(tip.equals("2")) wallet.setGorjetaStrategy(new GorjetaAltaStrategy());
		
		wallet.addObserver(new MobileAppObserver());
		wallet.addObserver(new LoggerObserver());
		
		int idTx = 0;
		String input = "";
		System.out.println("Pressione qualquer botão para realizar uma nova transação. Para sair, pressione \"0\"");		
		input = scan.nextLine();
		while(!input.equals("0")) {
			idTx++;
			System.out.println("(1) Enviar uma transação.\n(2) Receber uma transação.\n(3) Criar um contrato inteligente.");
			
			String txChoice = "";
			while(!txChoice.equals("1") && !txChoice.equals("2") && !txChoice.equals("3")) {
				txChoice = scan.nextLine();
			}
			
			String tipoTx = "";
			
			if(txChoice.equals("1")) {
				tipoTx = "envio";
				System.out.println("Qual conta está recebendo a transação?");
			}
	
			if(txChoice.equals("2")) {
				tipoTx = "receber";
				System.out.println("Qual conta está enviando a transação?");		
			}
			
			if(txChoice.equals("3")) {
				tipoTx = "criar_contrato";
				System.out.println("Qual o endereço do novo contrato?");
			}
			
			String outraConta = scan.nextLine();
			String valor = "";
			if(txChoice.equals("1") || txChoice.equals("2")) {
				System.out.println("Qual o valor da transação?");
				valor = scan.nextLine();
			}else {
				valor = "0";
			}
			
			wallet.processTransaction(new TransactionBuilder()
					.setAmount(new BigDecimal(valor))
					.setOtherAccount(outraConta)
					.setTxAddress("0x"+idTx)
					.setType(tipoTx)
					.build()
			);
			
			
			System.out.println("Pressione qualquer botão para realizar uma nova transação. Para sair, pressione \"0\"");		
			input = scan.nextLine();
		}					
		
		wallet.mostrarHistoricoTransaction();
	}
}
