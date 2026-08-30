import java.util.Scanner;

class challenge14 {
     static void main() {
         Scanner input=new Scanner(System.in);
         System.out.println("Enter the temperature in Fahrenheit");
         float fahrenheit=input.nextFloat();
         System.out.println("The temperature in Celsius "+((fahrenheit-32)*5/9));
     }
}
