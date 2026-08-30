public class challenge50 {
    static void main() {
        int[][] arr=arrayutilities.array2dcreation();
        int sum=0;
        if(arr.length==arr[0].length){
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    if(i==j||(i+j)==arr.length-1)
                        sum+=arr[i][j];
                }
            }
        }else {
            System.out.println("Sorry the sum of diagonals of an array can be found in a square matrix");
            return;
        }
        System.out.println("Sum= "+sum);
    }
}
