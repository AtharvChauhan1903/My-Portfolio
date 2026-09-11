public class challenge47 {
    static void main() {
        int[] arr = arrayutilities.arraycreation();
        int[] arr2 = arrayutilities.arraycreation();
        int length=arr.length;
        int length2= arr2.length;
        int[] arr3 = new int[length+length2];
        int j=0;
        int i=0;
        int k=0;
        while(j<length&&i<length2){
            if(arr[j]<arr2[i]){
                arr3[k++]=arr[j++];
            }
            else
                arr3[k++]=arr2[i++];
        }
        while(j<length){
            arr3[k++]=arr[j++];
        }
        while(i<length2){
            arr3[k++]=arr2[i++];
        }
        for(int g=0;g<arr3.length;g++)
            System.out.println(arr3[g]);
    }
}