import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,totalPeople,minValue;
    static int [][] populationMap;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        populationMap = new int[N][N];
        minValue = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            String [] tokens =br.readLine().split(" ");
            for(int j=0;j<N;j++){
                populationMap[i][j] = Integer.parseInt(tokens[j]);
                totalPeople+=populationMap[i][j];
            }
        }

        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                for(int d1=1;d1<N;d1++){
                    for(int d2=1;d2<N;d2++){
                        if(x+d1+d2 >= N) continue;
                        if(y-d1 < 0 || y+d2 >= N) continue;

                        solution(x,y,d1,d2);
                    }
                }
            }
        }
        bw.write(minValue+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution(int x,int y,int d1,int d2){
        boolean[][] border = new boolean[N][N];

        //경계선 설정
        for(int i=0;i<=d1;i++){
            border[x+i][y-i] = true;
            border[x+d2+i][y+d2-i] = true;
        }

        for(int i=0;i<=d2;i++){
            border[x+i][y+i] = true;
            border[x+d1+i][y-d1+i] = true;
        }

        int [] populationSum = new int[5];
        //1선거구
        for(int i=0;i<x+d1;i++){
            for(int j=0;j<=y;j++){
                if(border[i][j]) break;
                populationSum[0] += populationMap[i][j];
            }
        }

        //2선거구
        for(int i=0;i<=x+d2;i++){
            for(int j=N-1;j>y;j--){
                if(border[i][j]) break;
                populationSum[1] += populationMap[i][j];
            }
        }
        //3선거구
        for(int i=x+d1;i<=N-1;i++){
            for(int j=0;j<y-d1+d2;j++){
                if(border[i][j]) break;
                populationSum[2] += populationMap[i][j];
            }
        }

        //4선거구
        for(int i=x+d2+1;i<N;i++){
            for(int j=N-1;j>=y-d1+d2;j--){
                if(border[i][j]) break;
                populationSum[3] += populationMap[i][j];
            }
        }

        populationSum[4] = totalPeople;
        for(int i=0;i<4;i++){
            populationSum[4] -= populationSum[i];
        }
        Arrays.sort(populationSum);
        minValue = Math.min(minValue,populationSum[4]-populationSum[0]);
    }
}
