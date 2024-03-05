import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int N=0;    //N==바구니의 개수
        int M=0;    //M==반복하는 횟수
        N = kb.nextInt();
        M = kb.nextInt();
        int []ball = new int[N];
        for(int i=0;i<N;i++){
            ball[i]=i+1;
        }
        for(int x=0;x<M;x++){
            int i=kb.nextInt();
            int j=kb.nextInt();
            int count=(j-i+1)/2;
            for(int p=0;p<count;p++){
                if(i>j)
                break;
                int temp=ball[i-1];
                ball[i-1]=ball[j-1];
                ball[j-1]=temp;
                i++;
                j--;
            }
        }
        kb.close();
        for(int i=0;i<ball.length;i++){
            System.out.print(ball[i]+" ");
        }
    }
}
