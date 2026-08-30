package JavaPracticeQuestion3;

public class Execution {
    static void main() throws BankAccount.InsufficientBalanceException {
        BankAccount b1=new BankAccount(100000000,"Atharv Chauhan");
        BankAccount b2=new BankAccount(200,"Spider-Man");
        BankAccount b3=new BankAccount(600000,"Charm Caster");
        b3.deposit(5000);
        b1.deposit(5000);
        b2.deposit(5000);
        b1.withdraw(1);
        b2.withdraw(400);
        try {
            b3.withdraw(700000);
        } catch (BankAccount.InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(b1.getAccountNumber());
        System.out.println(b2.getAccountNumber());
        System.out.println(b3.getAccountNumber());
        System.out.println("Balances now....");
        System.out.println(b1.checkBalance());
        System.out.println(b2.checkBalance());
        System.out.println(b3.checkBalance());
    }
}
