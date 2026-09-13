import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    static void main() {
        Scanner input = new Scanner(System.in);
        ArrayList<Expense> ExpenseTracker = new ArrayList<>();
        int input2=0;
        while (true) {
            System.out.println("Enter the command Exit if you want to Exit or you can Write the description of item");
            String decript = input.nextLine();
            if (decript.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Enter the Amount");
            int num = input.nextInt();
            if (num <= 0) {
                System.out.println("Please run the program again and enter a positive integer");
                break;
            }
            input.nextLine();
            System.out.println("Enter the Category");
            String category = input.nextLine();
            Expense newExpense = new Expense(decript, num, category);
            ExpenseTracker.add(newExpense);
            System.out.println("Item added successfully");
        }

        if (ExpenseTracker.size() == 0) {
            System.out.println("There are no Expense Records");
        }
        System.out.println("Now the following services are available enter the corresponding number to access them");
        System.out.println("1)Total Expense\n2)Category Expense\n3)Highest Expense done\n4) Expense Filter");
        input2=input.nextInt();
        switch(input2){
            case 1: for (Expense e : ExpenseTracker) {
                System.out.println(e.toString());
            }
                int totalexpenditure = 0;
                for (Expense e : ExpenseTracker) {
                    totalexpenditure += e.getAmount();
                }
                System.out.println(totalexpenditure);

            break;
            case 2:input.nextLine();
                System.out.println("Enter the category whose expense you want to calculate");
                String SearchCategory = input.nextLine();

                int CategoryAmount = 0;
                for (Expense e : ExpenseTracker) {
                    if (e.getCategory().equalsIgnoreCase(SearchCategory)) {
                        CategoryAmount += e.getAmount();
                    }
                }
                System.out.println(CategoryAmount);
                break;
            case 3:Expense highest = ExpenseTracker.get(0);
                for (Expense e : ExpenseTracker) {
                    if (e.getAmount() > highest.getAmount()) {
                        highest = e;
                    }
                  }
                System.out.println("The highest expense is " + highest.toString());
             break;
            case 4: System.out.println("Enter the amount filter you want");
                int FilterAmount = input.nextInt();
                int greaterAmount = 0;
                for (Expense e : ExpenseTracker) {
                    if (e.getAmount() > FilterAmount) {
                        System.out.println(e.toString());
                        greaterAmount++;
                    }

                }
                if(greaterAmount==0){
                    System.out.println("No Expense was greater than user's input");
                }
                break;
            default: System.out.println("Invalid input rerun the program");
        }
        System.out.println("Thank you for using the Prototype of Expense Tracker");
    }
}