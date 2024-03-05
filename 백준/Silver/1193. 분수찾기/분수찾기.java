import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        int i=0;
        for(i=1;i<N;i++){
            N = N-i;
        }
        if(i%2==0){
            System.out.println(N+"/"+(i+1-N));
        }else{
            System.out.println(i+1-N+"/"+N);
        }
        kb.close();
    }
}
