import java.util.HashMap;
import java.util.Scanner;

public class JavaChallengequestion4 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your String input");
        String Sentence= input.nextLine();
        HashMap<String,Integer> wordCount=new HashMap<>();
        String[] words=Sentence.split("\\s+");
        for(String word:words){
            word=word.toLowerCase();
            wordCount.put(word,wordCount.getOrDefault(word,0)+1);
        }
        System.out.println(wordCount);
    }
}
