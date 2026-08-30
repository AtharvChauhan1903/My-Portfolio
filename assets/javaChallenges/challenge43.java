public class challenge43 {
    static void main() {
        int[] arr=arrayutilities.arraycreation();
        int count=0;
        int count2=0;
        for(int i=1;i<arr.length;i++){
            if(arr[i]>=arr[i-1]){
                count++;
            }
            if(arr[i]<=arr[i-1])
                count2++;
        }
        if(count== (arr.length-1))
            System.out.println("Yes the array is sorted");
        else if (count2==(arr.length-1)) {
            System.out.println("Yes the array is sorted");
        }
        else
            System.out.println("Unfortunately the array is not sorted");
    }
}
