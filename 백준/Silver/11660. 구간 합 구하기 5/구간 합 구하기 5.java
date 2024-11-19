import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M;
    static int x1,y1,x2,y2;
    static int [][] numbers;
    static int [][] prefixSum;

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        numbers = new int[N][N];
        prefixSum = new int[N][N];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                numbers[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        calcPreFixSum();

        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            x1 = Integer.parseInt(tokens[0])-1;
            y1 = Integer.parseInt(tokens[1])-1;
            x2 = Integer.parseInt(tokens[2])-1;
            y2 = Integer.parseInt(tokens[3])-1;

            calcSum(x1,y1,x2,y2);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void calcSum(int x1, int y1, int x2, int y2) throws IOException{
        int answer = prefixSum[x2][y2];
        if(x1>0) answer-=prefixSum[x1-1][y2];
        if(y1>0) answer-=prefixSum[x2][y1-1];
        if(x1>0 && y1>0) answer+=prefixSum[x1-1][y1-1];

        bw.write(answer+"\n");
    }

    private static void calcPreFixSum() {
        prefixSum[0][0] = numbers[0][0];
        for(int i=1;i<N;i++){
            prefixSum[i][0] = numbers[i][0] + prefixSum[i-1][0];
        }

        for(int i=1;i<N;i++){
            prefixSum[0][i] = numbers[0][i] + prefixSum[0][i-1];
        }

        for(int i=1;i<N;i++){
            for(int j=1;j<N;j++){
                prefixSum[i][j] = numbers[i][j] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];
            }
        }
    }
}
