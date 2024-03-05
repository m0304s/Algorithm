import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        int copy = N;
        int cnt=0;
        while(true){
            N = ((N%10)*10)+(((N/10)+(N%10))%10);
            cnt++;
            if(copy == N){
                break;
            }
        }
        System.out.println(cnt);
        kb.close();
    }
}
