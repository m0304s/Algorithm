
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String S = kb.next();
        int i = kb.nextInt();
        char[] arr = S.toCharArray();
        kb.close();
        System.out.println(arr[i-1]);;
    }
}
