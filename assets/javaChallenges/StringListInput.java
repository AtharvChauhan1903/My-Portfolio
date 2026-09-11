import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge108 {
    static void main() {
        List<String> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String ink = "";
        do {
            System.out.println("Enter the String input");
            ink = input.next();
            list.add(ink);
            System.out.println("Do you want to stop if yes then type (stop) if not type anything to re enter the value");
            ink = input.next();
        } while (!ink.equalsIgnoreCase("Stop"));
        String result=list.stream()
                .filter(str->str.length()<10)
                .reduce("",(a,b)->a.concat(b));
        System.out.println(result);
        }
    }

