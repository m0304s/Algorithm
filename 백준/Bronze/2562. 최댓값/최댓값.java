import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int [] array = new int[9];
        for(int i=0;i<9;i++){
            array[i]=kb.nextInt();
        }
        int max =-1111;
        int point = -1;
        for(int i=0;i<9;i++){
            if(max<array[i]){
                max=array[i];
                point = i+1;
            }
        }
        System.out.println(max);
        System.out.println(point);
        kb.close();
    }
}
