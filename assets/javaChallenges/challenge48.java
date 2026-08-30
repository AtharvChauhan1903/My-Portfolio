import java.util.Scanner;

public class challenge48 {
    static void main() {
        int[][] arr=arrayutilities.array2dcreation();
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the element you want to search");
        int find= input.nextInt();
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j]==find){
                    System.out.println("The number is in row "+(i+1)+" Column "+(j+1));
                    return;
                }

                }
            }
        System.out.println("Sorry number not Found");

        }
    }

