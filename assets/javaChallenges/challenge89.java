import java.util.Arrays;
import java.util.Scanner;

public class challenge89 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter how many inputs you want");
        int num= input.nextInt();
        String[] arr=new String[num];
        for(int i=0;i<arr.length;i++){
            System.out.println("Enter the input");
            String ink= input.next();
            arr[i]=ink;
        }
        System.out.println(concate(arr));
    }
    public static String concate(String...arr){
     String answer="";
     for(String result:arr){
         answer=answer.concat(result+" ");
     }
     return answer;
    }
}
