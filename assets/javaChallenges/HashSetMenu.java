
import java.util.HashSet;
import java.util.Scanner;

public class challenge95 {
    static void main() {
        HashSet<String> Name = new HashSet<>();
        Scanner input = new Scanner(System.in);
        System.out.println("This program will be loop until you command at the end is not end");
        String name = "";
        do {
            System.out.println("Welcome to hashset now you have 4 options  \n1)Add Element\n2)Remove Element\n3)Print the hashset");
            int num = input.nextInt();

            switch (num) {
                case 1:
                    System.out.println("Enter the String you want to add");
                    name = input.next();
                    Name.add(name);
                    break;
                case 2:
                    System.out.println("Enter the Element you want to remove");
                    name = input.next();
                    if (Name.contains(name)) {
                        Name.remove(name);
                    } else
                        System.out.println("No such element exists");
                    break;
                case 3:
                    System.out.println(Name);
                    break;
                default:
                    System.out.println("Wrong Input Try again");
                    break;
            }
            System.out.println("Enter your command");
            name= input.next();
        }while(!name.equalsIgnoreCase("Stop"));
    }
}
