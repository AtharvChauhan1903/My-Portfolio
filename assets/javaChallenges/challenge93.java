import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class challenge93 {
    static void main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the size of the array you want");
        int num = input.nextInt();
        ArrayList<Integer> arr = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            System.out.println("Enter the array element");
            arr.add(input.nextInt());
        }
        System.out.println(arr);
        System.out.println("Now the list will be reversed");
        Collections.reverse(arr);
        System.out.println(arr);
    }
}
