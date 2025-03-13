package main;

import java.util.Scanner;

class ValidadorManualTransaction extends ValidadorTransaction {
    @Override
    public boolean validar(Transaction tx) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("🛑 Aprovar transação? (y/n): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("y")) {
            System.out.println("✅ Transação aprovada!\n");
            return super.validar(tx);
        } else {
            System.out.println("❌ Transação rejeitada!\n");
            return false;
        }
    }
}