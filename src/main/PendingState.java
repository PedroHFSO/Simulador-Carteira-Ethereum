package main;

class PendingState implements TransactionState {
    @Override
    public String getStateName() {
        return "pendente";
    }
}