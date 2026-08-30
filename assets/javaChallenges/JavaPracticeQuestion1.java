import java.util.*;

public class JavaPracticeQuestion1 {
    static void main() {
        List<Integer> list=new ArrayList<>();
        Scanner input=new Scanner(System.in);
        String ink="";
        int num=0;
        do {
            System.out.println("Enter the Numerical input");
            num= input.nextInt();
            list.add(num);
            System.out.println("Do you want to stop if yes then type (stop) if not type anything to re enter the value");
            ink = input.next();
        } while (!ink.equalsIgnoreCase("Stop"));
        System.out.println("Highest value is: "+ Collections.max(list));
        System.out.println("Lowest value is: "+ Collections.min(list));
        double average=list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        System.out.println("Average is: "+average);
        list.stream().filter(Num->Num>average).forEach(System.out::println);
        HashSet<Integer>Unique=new HashSet<>(list);
        System.out.println("Unique Marks are "+Unique);
        list.stream().sorted().forEach(System.out::println);
    }
}
