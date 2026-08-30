import java.util.Scanner;

public class challenge56ternary {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("ENter the number");
        int num= input.nextInt();
        String result=(num>80)?"High":((num>50)?"Moderate":"Low");
        System.out.println(result);
    }
}
