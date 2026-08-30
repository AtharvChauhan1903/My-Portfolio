package challenge85;

public class challenge85 {
    public static int add(int num1,int num2){
        return num1+num2;
    }
    public static int add(int num1,int num2,int num3){
        return num1+num2+num3;
    }

    public static String add(String A,String B){

        return A.concat(B);
    }

    public static double add(double a,double b){
        return a+b;
    }
    static void main() {
        System.out.println(add(9,8));
        System.out.println(add("Timmy is ","A mythical being"));
        System.out.println(add(9.8,8.7));
        System.out.println(add(9,8,7));
    }
}
