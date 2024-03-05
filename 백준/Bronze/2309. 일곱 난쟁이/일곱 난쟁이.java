import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int [] array = new int[9];
        int sum = 0;
        for(int i=0;i<9;i++){
            array[i]=kb.nextInt();
            sum+=array[i];
        }
        for(int i=0;i<8;i++){
            for(int j=i+1;j<9;j++){
                if(sum-array[i]-array[j]==100){
                    array[i]=0;
                    array[j]=0;
                    Arrays.sort(array);
                    for(int x=2;x<9;x++){
                        System.out.println(array[x]);
                    }
                    return;
                }
            }
        }
        kb.close();
    }
}
