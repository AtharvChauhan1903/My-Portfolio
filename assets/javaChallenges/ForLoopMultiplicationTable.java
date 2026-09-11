import java.util.Scanner;

public class challenge61 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Which number's table do you want");
        int table= input.nextInt();
        for(int i=1;i<=10;i++){
            System.out.println(table+" times "+i+" ="+(table*i));
        }
        System.out.println("Stoopid lah...had it been your cousin timmy he would not have used this program and written the table.....before even you asked");
    }
}
