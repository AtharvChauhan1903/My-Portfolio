public class challenge49 {
    static void main() {
        int[][]arr=arrayutilities.array2dcreation();
        int totalelements=(arr.length)*(arr[0].length);
        int sum=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++)
                sum+=arr[i][j];
        }
        System.out.println("Sum is "+sum+" and the average is "+(sum/(float)totalelements));
    }
}
