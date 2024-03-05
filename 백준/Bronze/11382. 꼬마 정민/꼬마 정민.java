import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        long a=0,b=0,c=0;
        Scanner kb = new Scanner(System.in);
        a = kb.nextLong();
        b = kb.nextLong();
        c = kb.nextLong();
        kb.close();
        System.out.println(a+b+c);
    }
}