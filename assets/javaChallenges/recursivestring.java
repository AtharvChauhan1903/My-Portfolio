import java.util.Locale;
import java.util.Scanner;

public class recursivestring{

static boolean isPalindromeRec(String s, int left, int right) {


    if (left >= right)
        return true;


    if (s.charAt(left) != s.charAt(right))
        return false;


    return isPalindromeRec(s, left + 1, right - 1);
}

static boolean isPalindrome(String s) {
    return isPalindromeRec(s, 0, s.length() - 1);
}

public static void main(String[] args) {
    Scanner input=new Scanner(System.in);
    System.out.println("Enter the string");

    String s= input.next();
    s=s.toLowerCase();

    if (isPalindrome(s))
        System.out.println("true");
    else
        System.out.println("false");
}
}
