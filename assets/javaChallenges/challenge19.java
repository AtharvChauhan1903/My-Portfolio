import java.util.Scanner;

public class challege19 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your percentage");
        float marks= input.nextFloat();
        if(marks>90)
            System.out.println("Great job you get an A+");
        else if(marks>75)
            System.out.println("Good job Try to get A+ next time");
        else if(marks>60)
            System.out.println("Good Effort Try to get better result next time");
        else if(marks>30)
            System.out.println("Not good but at least you passed");
        else
            System.out.println("You failed try harder next time");
    }
}
