public class challenge40 {
    static void main() {
        int[] arr=arrayutilities.arraycreation();
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        float num= arr.length;
        float avg=sum/num;
        System.out.println("Sum= "+sum);
        System.out.println("Average: "+avg);
    }
}
