package main;

class FailedState implements TransactionState {
    @Override
    public String getStateName() {
        return "rejeitada";
    }
}
