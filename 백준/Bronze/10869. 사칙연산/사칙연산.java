import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int A;
        int B;
        Scanner kb = new Scanner(System.in);
        A = kb.nextInt();
        B = kb.nextInt();
        System.out.println(A+B);
        System.out.println(A-B);
        System.out.println(A*B);
        System.out.println(A/B);
        System.out.println(A%B);
        kb.close();
    }
}
