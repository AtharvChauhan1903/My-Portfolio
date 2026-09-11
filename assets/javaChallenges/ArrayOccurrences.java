import java.util.Scanner;

public class challenge41 {
    static void main() {
        Scanner input=new Scanner(System.in);
        int[] arr=arrayutilities.arraycreation();
        System.out.println("Enter the number whose occurrence you want to check");
        int num= input.nextInt();
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(num==arr[i]){
                count++;
            }
        }
        System.out.println("The number of this given number's Occurence is "+count );
    }
}
