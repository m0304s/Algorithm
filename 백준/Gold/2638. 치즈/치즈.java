import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int [] dx = {0,0,-1,1};
    static final int [] dy = {-1,1,0,0};
    static final int BLANK = 0, CHEESE = 1;

    static int R,C;
    static int [][] map;

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);
        map = new int[R][C];

        for(int i=0;i<R;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int time = 0;

        while(checkCheeseRemains()){
            markOutsideAir();
            melt();
            time++;
        }
        bw.write(time+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void markOutsideAir() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) map[i][j] = BLANK;
            }
        }

        boolean[][] visited = new boolean[R][C];
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            map[cur.x][cur.y] = -1; // 외부 공기 마킹

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (!inRange(nx, ny) || visited[nx][ny]) continue;
                if (map[nx][ny] != BLANK) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }
    }

    private static void melt() {
        List<Node> melts = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == CHEESE && isTargetMelt(i, j)) {
                    melts.add(new Node(i, j));
                }
            }
        }

        for (Node node : melts) {
            map[node.x][node.y] = BLANK;
        }
    }


    private static boolean isTargetMelt(int x, int y) {
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny)) continue;
            if (map[nx][ny] == -1) count++; // 외부 공기인 경우만 카운트
        }
        return count >= 2;
    }

    private static boolean checkCheeseRemains(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == CHEESE) return true;
            }
        }
        return false;
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}