import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge109 {
    static void main() {
        List<Integer> list=new ArrayList<>();
        Scanner input=new Scanner(System.in);
        String ink = "";
        do {
            System.out.println("Enter the Numerical input");
            int num=input.nextInt();
            list.add(num);
            System.out.println("Do you want to stop if yes then type (stop) if not type anything to re enter the value");
            ink = input.next();
        } while (!ink.equalsIgnoreCase("Stop"));
          list.stream().filter(num->num%2!=0)
                  .forEach(System.out::println);
    }
}
