public class challenge63 {
    static void main() {
        int[] arr=arrayutilities.arraycreation();
        int maximum=arr[0];
        for(int num:arr){
            if(maximum<=num)
                maximum=num;
        }
        System.out.println("The greatest element in the given array is "+maximum);
    }
}
