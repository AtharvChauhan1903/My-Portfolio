public class challenge45 {
    static void main() {
        int[] arr=arrayutilities.arraycreation();
        for(int i=0;i<(arr.length/2);i++){
            int why=arr[i];;
            arr[i]=arr[arr.length-1-i];
            arr[arr.length-1-i]=why;

        }
        for(int j=0;j<arr.length;j++){
            System.out.print(arr[j]+" ");
        }
    }
}
