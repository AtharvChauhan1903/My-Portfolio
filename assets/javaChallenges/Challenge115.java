import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge115 {
    static void main() {
        List<String> list=new ArrayList<>();
        Scanner input=new Scanner(System.in);
        String ink="";

        do {
            System.out.println("Enter the Numerical input");
             ink = input.next();
            list.add(ink);
            System.out.println("Do you want to stop if yes then type (stop) if not type anything to re enter the value");
            ink = input.next();
        } while (!ink.equalsIgnoreCase("Stop"));
        int sum=list.stream().map(Integer::parseInt).mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}
