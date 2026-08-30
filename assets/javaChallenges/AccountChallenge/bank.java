package Account;

public class bank {
    static void main() {
        Account ac1=new Account("Atharv",30000);
        ac1.setAccountNumber(1120);

        ac1.withdraw(1000000000);
        System.out.println(ac1.getBalance());
        ac1.deposit(123456);
        System.out.println(ac1.getBalance());
        ac1.withdraw(2345);
        System.out.println(ac1.getBalance());
        System.out.println(ac1.getBalance());
    }
}
