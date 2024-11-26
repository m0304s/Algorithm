import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [][] map;
    static int [] paper = new int[]{0,5,5,5,5,5};
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        map = new int[10][10];
        for(int i=0;i<10;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<10;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        dfs(0,0,0);
        if(minValue == Integer.MAX_VALUE){
            bw.write("-1\n");
        }else{
            bw.write(minValue+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int count) {
        if (x >= 9 && y > 9) {
            minValue = Math.min(minValue, count);
            return;
        }
        if (count >= minValue) return; // 가지치기

        if (y >= 10) { // 다음 행으로 이동
            dfs(x + 1, 0, count);
            return;
        }

        if (map[x][y] == 1) { // 현재 위치가 1이라면 색종이 붙이기 시도
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && canAttach(x, y, i)) {
                    attach(x, y, i, 0); // 색종이를 붙임
                    paper[i]--;
                    dfs(x, y + 1, count + 1); // 다음 위치 탐색
                    attach(x, y, i, 1); // 색종이를 떼어냄
                    paper[i]++;
                }
            }
        } else {
            dfs(x, y + 1, count); // 현재 위치가 0이라면 다음 위치로 이동
        }
    }


    private static void attach(int x,int y,int size,int state){
        for(int i=x;i<x+size;i++){
            for(int j=y;j<y+size;j++){
                map[i][j] = state;
            }
        }
    }

    private static boolean canAttach(int x,int y,int size){
        for(int i=x;i<x+size;i++){
            for(int j=y;j<y+size;j++){
                if(!inRange(i,j)) return false;
                if(map[i][j] != 1) return false;
            }
        }
        return true;
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
}
