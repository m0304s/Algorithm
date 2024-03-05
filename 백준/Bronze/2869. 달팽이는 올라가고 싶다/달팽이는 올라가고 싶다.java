import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int A = kb.nextInt();
        int B = kb.nextInt();
        int V = kb.nextInt();

        int heightOfDay=A-B;
        int day =0;
        if(A>V){
            day=1;
        }else{
            if((V-A)%heightOfDay==0){
                day = (V-A)/heightOfDay+1;
            }else{
                day=(V-A)/heightOfDay+2;
            }
        }
        System.out.println(day);
        kb.close();
    }
}
