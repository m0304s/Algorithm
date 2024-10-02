import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int PIPE = 2;
    
    static int N;
    static int [][] map;

    static int answer;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        answer = 0;
        for(int i=1;i<=N;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j+1] = Integer.parseInt(tokens[j]);
            }
        }
        // 파이프 초기 위치: (1,1) (1,2)
        dfs(1,2,0);
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int x,int y,int direction){
        if(x == N && y == N){
            answer++;
            return;
        }

        switch (direction) {
            case 0:     //파이프가 가로 방향인 경우, 동쪽과 대각선 방향 이동 가능
                if(y+1 <= N && map[x][y+1] == 0){
                    dfs(x, y+1, 0);
                }
                break;
            case 1:     //파이프가 세로 방향인 경우, 남쪽과 대각선 방향 이동 가능
                if(x+1 <= N && map[x+1][y] == 0){
                    dfs(x+1, y, 1);
                }
                break;
            case 2:     //파이프가 대각선 방향인 경우, 동쪽과 남쪽, 대각선 이동 가능
                if(y+1 <= N && map[x][y+1] == 0){
                    dfs(x, y+1, 0);
                }
                if(x+1 <= N && map[x+1][y] == 0){
                    dfs(x+1, y, 1);
                }
                break;
        }

        // 대각선 방향 이동 가능 체크
        if (y + 1 <= N && x + 1 <= N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
            dfs(x + 1, y + 1, 2);
        }
    }
}
