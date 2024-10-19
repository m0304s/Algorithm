import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static int p,q;
    static int [][] map = new int[300][300];

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            p = Integer.parseInt(tokens[0]);
            q = Integer.parseInt(tokens[1]);
            drawMap();
            int answer = starCalculator();
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int starCalculator(){
        int answer = 0;
        int[] first = andCalculator(p);
        int [] second = andCalculator(q);

        answer = sharpCalculator(first[0]+second[0],first[1]+second[1]);
        return answer;
    }

    static int[] andCalculator(int num){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                if(num == map[i][j]) return new int[]{i+1,j+1};
            }
        }return null;
    }
    static int sharpCalculator(int x,int y){
        return map[x-1][y-1];
    }
    static void drawMap() {
        int num = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j <= i; j++) {
                map[i-j][j] = num++;
            }
        }
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map.length - i; j++) {
                map[map.length-1-j][i+j] = num++;
            }
        }
    }
}
