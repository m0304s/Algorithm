
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb= new Scanner(System.in);
        while(kb.hasNextLine()){
            String S = kb.nextLine();
            System.out.println(S);
        }
        kb.close();
    }
}
