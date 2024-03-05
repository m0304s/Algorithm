import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int N = kb.nextInt();
        kb.close();
        for(int x = 1;x<=N;x++){
            for(int y = N-x;y>0;y--){
                System.out.print(" ");
            }
            for(int z = 1;z<=2*(x-1)+1;z++){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int x = 1;x<N;x++){
            for(int y = 1;y<=x;y++){
                System.out.print(" ");
            }
            for(int z = 1;z<=2*(N-x)-1;z++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
