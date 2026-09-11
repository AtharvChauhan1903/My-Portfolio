public class challenge46 {
    static void main() {
        int[] arr=arrayutilities.arraycreation();
        int[] arr2=arrayutilities.arraycreation();

        if(arr.length==arr2.length){
            for(int i=0;i<arr.length;i++){
                if(arr[i]!=arr2[i]){
                    System.out.println("Unfortunately the arrays are not  equal");
                    return;
                }

            }
            System.out.println("The elements of the array are equal ");
        }else
            System.out.println("The arrays don't have the same number of elements");

    }
}
