package JavaPracticeQuestion3;

public class BankAccount {
    private final int AccountNumber;
    public String AccountHolderName;
    private int balance;
    public class InsufficientBalanceException extends Exception {

        public InsufficientBalanceException(String message) {
            super(message);
        }
    }

    public BankAccount(int balance, String accountHolderName) {
        this.balance = balance;
        this.AccountNumber = (int)(Math.random()*1000);
        this.AccountHolderName = accountHolderName;
    }

    public int getAccountNumber() {
        return this.AccountNumber;
    }

    public String getAccountHolderName() {
        return this.AccountHolderName;
    }

    public int getBalance() {
        return this.balance;
    }
    public void deposit(int amount){
        if(amount>0)
            balance+=amount;
        else
            System.out.println("Invalid amount deposited");

    }
    public void withdraw(int amount) throws InsufficientBalanceException {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new InsufficientBalanceException("Insufficient balance or invalid amount");
        }
    }
      public int checkBalance(){
        return getBalance();
      }
}
