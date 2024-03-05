import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N,M;    //N==바구니의 개수, M==반복하는 횟수
        Scanner kb = new Scanner(System.in);
        N=kb.nextInt();
        M=kb.nextInt();
        int []ball = new int[N];
        for(int i=0;i<N;i++){
            ball[i]=i+1;
        }
        for(int x=0;x<M;x++){
            int i=kb.nextInt();
            int j=kb.nextInt();
            int prev;
            prev=ball[i-1];
            ball[i-1]=ball[j-1];
            ball[j-1]=prev;
        }
        kb.close();
        for(int x=0;x<ball.length;x++){
            System.out.print(ball[x]+" ");
        }
    }
}
