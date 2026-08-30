import java.util.Scanner;

public class challenge64 {
    static void main() {
        Scanner input=new Scanner(System.in);
        int[] arr=arrayutilities.arraycreation();
        System.out.println("Choose the number you want to choose");
        int num= input.nextInt();
        int count=0;
        for(int k:arr){
            if(k==num)
                count++;
        }
        if(count<1)
            System.out.println("The element doesn't exist in the array");
        else
            System.out.println("The element has repeated itself "+count+" times");
    }
}
