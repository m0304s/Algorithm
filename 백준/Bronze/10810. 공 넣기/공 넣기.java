import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N=0,M=0;    //N==공의 번호, M==반복하는 횟수
        Scanner kb = new Scanner(System.in);
        N = kb.nextInt();
        M = kb.nextInt();
        int [] ball = new int[N];
        for(int x = 0;x<M;x++){
            int i = kb.nextInt();
            int j = kb.nextInt();
            int k = kb.nextInt();
            //i~j번 바구니에 K번 번호가 적혀있는 공을 넣는다. 반복문
            for(int y = i;y<=j;y++){
                ball[y-1]=k;
            }
        }
        kb.close();
        for(int z=0;z<ball.length;z++){
            System.out.print(ball[z]+" ");
        }
    }
}
