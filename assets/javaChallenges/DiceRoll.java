import java.util.Random;

public class DiceRoll {
    static void main() {
        Random random=new Random();
        int diceRoll= random.nextInt(6)+1;
        System.out.println("The dice rolled "+diceRoll);
    }
}
