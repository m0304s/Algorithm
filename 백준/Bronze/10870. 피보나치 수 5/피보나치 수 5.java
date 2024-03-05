import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        kb.close();
        feat f = new feat();
        int result = f.fibonacci(N);
        System.out.println(result);
    }
    public static class feat{
        public int fibonacci(int N){
            if(N==2){
                return 1;
            }else if(N==1){
                return 1;
            }else if(N==0){
                return 0;
            }else{
                return fibonacci(N-1)+fibonacci(N-2);
            }
        }
    }
}
