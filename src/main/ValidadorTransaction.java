package main;

public abstract class ValidadorTransaction {
	protected ValidadorTransaction proximo;

    public void setProximo(ValidadorTransaction proximo) {
        this.proximo = proximo;
    }

    public boolean validar(Transaction tx) {
        if (proximo != null) {
            return proximo.validar(tx);
        }
        return true;
    }
}
