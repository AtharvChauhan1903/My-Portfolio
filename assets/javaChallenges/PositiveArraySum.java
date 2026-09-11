public class challenge66 {
    static void main() {
        int[] arr=arrayutilities.arraycreation();
        int sum=0;
        for(int num:arr){
            if(num<0)
                continue;
            sum+=num;
        }
        System.out.println(sum);
    }
}
