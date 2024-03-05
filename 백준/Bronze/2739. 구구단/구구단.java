import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int num = kb.nextInt();
        for(int i=1;i<10;i++){
            System.out.println(num+" * "+i+" = "+num*i);
        }
        kb.close();
    }
}
