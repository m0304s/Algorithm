import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int test = kb.nextInt();
        for(int i=0;i<test;i++){
            int N = kb.nextInt();
            int [] arr = new int[N];
            int sum=0;
            for(int j=0;j<N;j++){
                arr[j]=kb.nextInt();
                sum+=arr[j];
            }
            double avg = sum/N;
            int cnt=0;
            for(int j=0;j<N;j++){
                if(arr[j]>avg){
                    cnt++;
                }
            }
            double cntd = (double)cnt;
            double result = cntd/N;
            System.out.print(String.format("%.3f", result*100));
            System.out.println("%");
        }
        kb.close();
    }
}
