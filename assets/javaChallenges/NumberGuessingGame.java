import java.util.Random;
import java.util.Scanner;

public class challenge60 {
    static void main() {
        Scanner input=new Scanner(System.in);
        Random random=new Random();
        int answer= random.nextInt(10)+1;
        int x;
        int trial=0;
        do{
            System.out.println("Guess the answer correctly if you dare");
             x= input.nextInt();
             if(x<answer)
                 System.out.println("Too Low You suck bro just give up");
             else if(x>answer)
                 System.out.println("Too High Genius are you even trying");
             trial++;
        }while(answer!=x);
        System.out.println("Good job smart guy");
        System.out.println("Congrats genius you figured it out in "+trial+" Attempts,Had it been your cousin timmy he would have done it in "+(trial-1)+" Attempt");
    }
}
