import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class challenge90 {
    static void main() {
        Scanner input=new Scanner(System.in);
        List<String>userInput=new ArrayList<>();
        System.out.println("Enter the number of inputs you want");
        int num= input.nextInt();
        for(int i=0;i<num;i++){
            System.out.println("Enter the string value");
            String k= input.next();
            userInput.add(k);
        }
        userInput.sort(String::compareTo);
        for(String str:userInput)
            System.out.println(str);
    }
}
