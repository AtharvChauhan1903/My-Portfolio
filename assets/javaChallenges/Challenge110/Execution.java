package Challenge110;

public class Execution {
    static void main() {
        Primeornot prime=num -> {
            for(int i=2;i<num;i++){
                if(num%i==0)
                    return false;

            }
            return true;
        };
        System.out.println(prime.isPrime(7));
    }
}
