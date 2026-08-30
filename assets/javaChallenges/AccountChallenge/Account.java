package Account;

public class Account {
    private int balance;
    private int accountNumber;
    final private String accountholdername;

    public Account(String accountholdername,int balance){
        this.accountholdername=accountholdername;
        if(balance>0)
         this.balance=balance;
        else
            System.out.println("Rerun the program and set a positive number as balance or are you dumb ");
    }

    public int getBalance() {
        return balance;
    }

    public String getAccountholdername() {
        return accountholdername;
    }



    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        if(accountNumber>0) {
            this.accountNumber = accountNumber;
        }else
            System.out.println("Rerun the function an dthis time set it to something positive and be serious about this ,it is your bank number");
    }
    public void deposit(int sum){
        if(sum>0)
            balance+=sum;

    }
    public void withdraw(int sum){
        if(sum<balance){
            System.out.println("Amount deducted succesfully");
            balance-=sum;
        }else if(sum==balance){
            System.out.println("The amount is deducted but now you have no balance at all");
            balance-=sum;
        }else
            System.out.println("Cannot withdraw more than the balance available in bank");
    }

}
