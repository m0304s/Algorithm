import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean checkAnswer(int [][] map, int N){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]!=(i*N+(j+1))){
                    return false;
                }
            }
        }
        return true;
    }

    static int [][] tranpose(int [][] map, int size){
        for(int i=0;i<=size;i++){
            for(int j=i+1;j<=size;j++){
                int temp = map[i][j];
                map[i][j] = map[j][i];
                map[j][i] = temp;
            }
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int N = Integer.parseInt(br.readLine());
            int [][] map = new int[N][N];
            int [][] answer = new int[N][N];
            int count = 0;
            map = createMap(map, N);
            answer = createAnswerMap(answer,N);

            for(int n = N-1;n>=0;n--){
                if(map[0][n] != answer[0][n] || map[n][0] != answer[n][0]){
                    tranpose(map,n);
                    count++;
                }
            }
            bw.write(count+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int [][] createMap(int [][] map, int N) throws IOException{
        for(int i=0;i<N;i++){
            String [] string = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(string[j]);
            }
        }
        return map;
    }
    static int [][] createAnswerMap(int [][]map, int N){
        int count = 1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = count;
                count++;
            }
        }
        return map;
    }
}
