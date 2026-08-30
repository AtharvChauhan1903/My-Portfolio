import java.util.Scanner;

public class challenge71 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the first string");
        String first= input.nextLine();
        System.out.println("Enter the second String");
        String second=input.nextLine();
        String combine=first.concat("").concat(second);
        combine=combine.toUpperCase();
        System.out.println(combine);
    }
}
