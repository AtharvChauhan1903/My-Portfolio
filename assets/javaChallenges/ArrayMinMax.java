public class challenge42 {
    static void main() {
        int[]arr=arrayutilities.arraycreation();
        int smallest=arr[0];
        int largest=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]<smallest){
                smallest=arr[i];
            }
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i]>largest){
                largest=arr[i];
            }
        }
        System.out.println("Smallest element in array is "+smallest);
        System.out.println("Largest element in array is "+largest);
    }
}
