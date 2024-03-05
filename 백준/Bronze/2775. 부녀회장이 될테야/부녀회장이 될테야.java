import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int [][] arr = new int[15][15];
        for(int i=0;i<15;i++){
            arr[0][i] = i;
        }
        for(int i=1;i<15;i++){
            for(int j=1;j<15;j++){  //arr[i-1][j] = 아래층 동일 호수에 사는 사람
                arr[i][j] = arr[i-1][j] + arr[i][j-1];
            }
        }
        int T = kb.nextInt();
        for(int i=0;i<T;i++){
            int K = kb.nextInt();   //K층
            int N = kb.nextInt();   //N호
            System.out.println(arr[K][N]);
        }
        kb.close();
    }
}
