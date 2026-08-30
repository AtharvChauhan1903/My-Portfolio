package challenge92;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class challenge92 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the size of the array you want");
        int num= input.nextInt();
        ArrayList<Integer>arr=new ArrayList<>(num);
        for(int i=0;i<num;i++){
            System.out.println("Enter the array element");
           arr.add(input.nextInt());
        }
        for(int i=0;i<num;i++){
            System.out.println(arr.get(i));
        }
        System.out.println("Enter the elements you want to swap(enter the index of element)");
        int nuk= input.nextInt();
        int nuk2= input.nextInt();
        try{
            Collections.swap(arr,nuk-1,nuk2-1);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            return;
        }
        for(int i=0;i<num;i++){
            System.out.println(arr.get(i));
        }


    }
}
