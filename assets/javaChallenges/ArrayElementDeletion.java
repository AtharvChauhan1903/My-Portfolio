import java.util.Scanner;

public class challenge44 {
    static void main() {
        Scanner input=new Scanner(System.in);
        int[] arr=arrayutilities.arraycreation();
        System.out.println("Enter the element you want to delete");
        int num= input.nextInt();
        int i=0;
        for( i=0;i<arr.length;i++) {
            if (arr[i] == num) {
                break;
            }
        }
            while(i<(arr.length-1)) {
                arr[i] = arr[i + 1];
                i++;
            }



        for( i=0;i<(arr.length-1);i++){
            System.out.print(arr[i]+" ");
        }

    }
}
