
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int T = kb.nextInt();
        for(int x=0;x<T;x++){
            String S = kb.next();
            char []arr = S.toCharArray();
            int length = S.length();
            System.out.print(arr[0]);
            System.out.print(arr[length-1]);
            System.out.println("");
        }
        kb.close();
    }
}
