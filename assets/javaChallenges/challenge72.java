import java.util.Scanner;

public class challenge72 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the radius of the circle");
        float radius= input.nextFloat();
        double circumference=2*radius*Math.PI;
        double area=Math.PI*Math.pow(radius,2);
        System.out.println("Circumference is "+circumference);
        System.out.println("Area is "+area);
    }
}
