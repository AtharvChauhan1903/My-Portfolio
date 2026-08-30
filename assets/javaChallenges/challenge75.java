import java.util.Scanner;

public class challenge75 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("How long do you want your Array to be");
        int num= input.nextInt();
        input.nextLine();
        String mess="";
        String[] arr=new String[num];
        for(int i=0;i<arr.length;i++){
            System.out.println("Enter the string element");
            mess= input.nextLine();
            arr[i]=mess;
        }
        System.out.println("Array successfully created");
        StringBuilder messa=new StringBuilder("");
        for(int i=0;i<arr.length;i++){
            messa.append(arr[i]+" ");
            System.out.println(messa);
        }

    }
}
