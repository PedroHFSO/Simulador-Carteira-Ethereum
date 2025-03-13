package main;

class ConfirmedState implements TransactionState {
    @Override
    public String getStateName() {
        return "confirmada";
    }
}