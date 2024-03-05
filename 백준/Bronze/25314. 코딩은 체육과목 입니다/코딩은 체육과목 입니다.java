import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long N = 0;
        Scanner kb = new Scanner(System.in);
        N = kb.nextLong();
        kb.close();
        long k = N/4;
        for(long x = 0;x<k;x++){
            System.out.print("long ");
        }
        System.out.println("int");
    }
}
