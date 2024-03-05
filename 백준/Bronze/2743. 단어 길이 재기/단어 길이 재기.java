import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String S = kb.next();
        kb.close();
        System.out.println(S.toCharArray().length);
    }
}
