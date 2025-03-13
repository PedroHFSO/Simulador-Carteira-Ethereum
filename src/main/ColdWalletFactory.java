package main;

public class ColdWalletFactory implements WalletFactory {
    @Override
    public Wallet getWallet() {
        if (Wallet.getInstance() == null) {
        	Wallet.setInstance(new ColdWallet("0xEnderecoColdWallet"));
        }
        return Wallet.getInstance();
    }
}