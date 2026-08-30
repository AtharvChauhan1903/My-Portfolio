import java.util.Scanner;

public class challenge68 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the range");
        int num= input.nextInt();
        for(int i=0;i<num;i++)
            System.out.print(recure(i));
    }
    static int recure(int num) {
        if (num <= 1)
            return num;
        return recure(num-1)+recure(num-2);
    }
}
