import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int [] array = new int[10];
        for(int i=0;i<10;i++){
            array[i] = kb.nextInt();
        }
        int sum=0;
        for(int i=0;i<10;i++){
            sum+=array[i];
            if(sum==100){
                break;
            }
            if(sum>100){
                if(sum-100 <= 100-(sum-array[i])){
                    break;
                }else{
                    sum=sum-array[i];
                }
                break;
            }
        }
        System.out.println(sum);
        kb.close();
    }
}
