import java.io.*;
import java.util.HashSet;

public class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static String [][] map;
    static HashSet<String> numWithSevenLengthSet = new HashSet<>();
    static int N = 4;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            map = new String[N][N];
            numWithSevenLengthSet.clear();
            for(int i=0;i<N;i++){
                String []tokens = br.readLine().split(" ");
                for(int j=0;j<N;j++){
                    map[i][j] = tokens[j];
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    dfs(i,j,0,map[i][j]);
                }
            }
            bw.write("#"+t+" "+numWithSevenLengthSet.size()+"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
    static void dfs(int x,int y,int depth,String input){
        if(depth == 6){
            numWithSevenLengthSet.add(input);
            return;
        }
        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(!inRange(nx,ny)) continue;

            dfs(nx,ny,depth+1,input+map[nx][ny]);
        }
    }
    static boolean inRange(int x,int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}
