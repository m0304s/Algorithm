import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] guyoung;
    static int [] minyoung;
    static int [] minyoungPerm = new int[9];
    static boolean[] visited;

    static int guyoungCnt;
    static int minyoungCnt;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            guyoung = new int[9];
            minyoung = new int[9];
            visited = new boolean[9];

            guyoungCnt = 0;
            minyoungCnt = 0;

            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<tokens.length;i++){
                guyoung[i] = Integer.parseInt(tokens[i]);
            }
            Set<Integer> guyoungSet = new HashSet<>();
            for (int i : guyoung) {
                guyoungSet.add(i);
            }
            int index = 0;
            for(int i=1;i<=18;i++){
                if(!guyoungSet.contains(i)) minyoung[index++] = i;
            }

            calcScore(0);
            bw.write("#" + t + " " + guyoungCnt + " " + minyoungCnt + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void calcScore(int depth){
        if(depth == 9){
            int guyoungScore = 0;
            int minyoungScore = 0;

            for(int i=0;i<9;i++){
                if(guyoung[i] > minyoungPerm[i]){
                    guyoungScore+=(guyoung[i] + minyoungPerm[i]);
                }else if(guyoung[i] < minyoungPerm[i]){
                    minyoungScore+=(guyoung[i] + minyoungPerm[i]);
                }
            }

            if(guyoungScore > minyoungScore){
                guyoungCnt++;
            }else if(guyoungScore < minyoungScore){
                minyoungCnt++;
            }
            return;
        }

        for(int i=0;i<9;i++){
            if(!visited[i]){
                visited[i] = true;
                minyoungPerm[depth] = minyoung[i];
                calcScore(depth+1);
                visited[i] = false;
            }
        }
    }
}
