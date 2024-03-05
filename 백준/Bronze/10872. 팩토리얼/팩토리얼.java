import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        kb.close();
        int result = factorial(N);
        System.out.println(result);
    }
    public static int factorial(int N){
        if(N!=0){
            return N * factorial(N-1);
        }else{
            return 1;
        }
    }
}
