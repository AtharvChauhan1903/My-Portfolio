import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Challenge107 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter how many elements you want in a the array");
        int num= input.nextInt();
        String[] arr=new String[num];
        for(int i=0;i<arr.length;i++){
            System.out.println("Enter the array value");
            String str= input.next();
            arr[i]=str;
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        Arrays.stream(arr).forEach(System.out::println);

    }
}
