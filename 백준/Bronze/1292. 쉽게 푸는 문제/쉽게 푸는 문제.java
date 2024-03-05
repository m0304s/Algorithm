import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int A = kb.nextInt();
        int B = kb.nextInt();
        kb.close();

        int sum =0;
        int index=0;

        for(int i=1;i<=B;i++){   // i -> 반복되는 특정 숫자
            int count=0;    //특정 숫자가 몇번 반복되었는지
            for(int j=0;j<i;j++){
                count++;
                index++;    //전체 기준 몇 번째 숫자인지
                if(index>=A && index<=B){
                    sum+=i;
                }
                if(index>B){
                    break;
                }
            }
        }
        System.out.println(sum);
        // 1
        // 2 2
        // 3 3 3
        // 4 4 4 4
        // 5 5 5 5 5
        // 6 6 6 6 6 6
        // 7 7 7 7 7 7 7
        // ...
    }
}
