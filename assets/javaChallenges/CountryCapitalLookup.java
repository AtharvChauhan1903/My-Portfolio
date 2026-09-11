import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class challenge98 {
    static void main() {
        HashMap<String, String> Map=new HashMap<>();
        Map.put("India","New Delhi");
        Map.put("Japan","Tokyo");
        Map.put("MyDreamWorld","ThePlaceNamedAfterMe");
        System.out.println(Map);
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your country");
        String S= input.next();
        if(Map.containsKey(S)){
            System.out.println(Map.get(S));
        }

    }
}
