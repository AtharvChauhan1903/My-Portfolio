import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class challenge91copy {
    static void main() {
        Scanner input = new Scanner(System.in);
        Collection<String> Arr = new ArrayList<>();
        System.out.println("Enter the number of inputs you want in the list");
        int num = input.nextInt();
        for (int i = 0; i < num; i++) {
            System.out.println("Enter the value");
            String str = input.next();
            Arr.add(str);
        }
        for (String str : Arr) {
            System.out.println(str + "->" + Collections.frequency(Arr, str));
        }

    }
}

