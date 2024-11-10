import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static EarchWorm[] worms;
    static int [] output;
    static boolean [] selected;
    static long MIN_DISTANCE;

    static class EarchWorm{
        int x,y;
        public EarchWorm(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            worms = new EarchWorm[N];
            selected = new boolean[N];
            MIN_DISTANCE = Long.MAX_VALUE;
            for(int i=0;i<N;i++){
                String [] tokens = br.readLine().split(" ");
                worms[i] = new EarchWorm(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));
            }
            dfs(0,0);
            bw.write("#"+t+" "+MIN_DISTANCE+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void dfs(int depth, int count){
        if(count == N/2){
            calculateVector();
            return;
        }
        if(depth == N) return;

        selected[depth] = true;
        dfs(depth+1,count+1);
        selected[depth] = false;
        dfs(depth+1,count);
    }

    private static void calculateVector() {
        long distance = 0;
        long sumX = 0;
        long sumY = 0;

        for(int i=0;i<N;i++){
            if(selected[i]){
                sumX+=worms[i].x;
                sumY+=worms[i].y;
            }else{
                sumX-=worms[i].x;
                sumY-=worms[i].y;
            }
        }

        distance = sumX*sumX+sumY*sumY;
        MIN_DISTANCE = Math.min(MIN_DISTANCE,distance);
    }
}
