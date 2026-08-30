import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Challenge113 {
    static void main() {
        List<Integer> list=new ArrayList<>();
        Scanner input=new Scanner(System.in);
        String ink="";
        int num=0;
        do {
            System.out.println("Enter the Numerical input");
            num = input.nextInt();
            list.add(num);
            System.out.println("Do you want to stop if yes then type (stop) if not type anything to re enter the value");
            ink = input.next();
        } while (!ink.equalsIgnoreCase("Stop"));
        List<Integer>lis=new ArrayList<>(list.stream()
                .distinct().collect(Collectors.toList()));
        lis.stream().forEach(System.out::println);
    }
}
