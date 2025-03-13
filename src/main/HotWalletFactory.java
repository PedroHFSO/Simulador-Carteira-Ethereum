package main;

public class HotWalletFactory implements WalletFactory {
    @Override
    public Wallet getWallet() {
        if (Wallet.getInstance() == null) {
        	Wallet.setInstance(new HotWallet("0xEnderecoHotWallet"));
        }
        return Wallet.getInstance();
    }
}