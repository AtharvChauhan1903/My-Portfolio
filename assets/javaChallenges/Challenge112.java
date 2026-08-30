package Challenge112;

import java.util.Optional;
import java.util.Scanner;

public class Challenge112 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the string you want to enter");
        String str= input.next();
        System.out.println(opt(str));
    }
    public static Optional<String> opt(String str){
        if(str==null||str.isEmpty())
            return Optional.empty();
        return Optional.of(str.toUpperCase());
    }

}
