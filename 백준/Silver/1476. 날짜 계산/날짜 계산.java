import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int E = kb.nextInt();   //1<=E<=15
        int S = kb.nextInt();   //1<=S<=28
        int M = kb.nextInt();   //1<=M<=19
        kb.close();

        int count=0;
        int count_e=0;
        int count_s=0;
        int count_m=0;
        while(true){
            if(count_e>15){
                count_e=1;
            }
            if(count_s>28){
                count_s=1;
            }
            if(count_m>19){
                count_m=1;
            }
            count++;
            if(E==count_e && S == count_s && M == count_m){
                System.out.println(count-1);
                break;
            }
            count_e++;
            count_s++;
            count_m++;

        }
    }
}
