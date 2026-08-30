import java.util.*;

public class challenge91 {
    static void main() {
        Scanner input=new Scanner(System.in);
        Collection<String> Arr=new ArrayList<>();
        System.out.println("Enter the number of inputs you want in the list");
        int num= input.nextInt();
        for(int i=0;i<num;i++){
            System.out.println("Enter the value");
            String str= input.next();
            Arr.add(str);
        }
        System.out.println("Now enter the entity whose frequency you want to check");
        String str= input.next();
        int count= Collections.frequency(Arr,str);
        if(count==0)
            System.out.println("The element was never added");
        else
            System.out.println("The element was repeated "+count+" times");

    }
}
