import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int BLANK = 0, CHEESE = 1;
    static final int [] dx = {0,0,-1,1};
    static final int [] dy = {-1,1,0,0};
    static int [][] map;
    static int R, C;

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
        int lastCount = 0;
        while(isCheeseRemains()){
            int meltSize = meltCheese();
            if(meltSize == 0) break;
            lastCount = meltSize;
            time++;
        }

        bw.write(time+"\n"+lastCount+"\n");
        bw.flush();
        bw.close();
    }

    private static int meltCheese(){
        Queue<Node> queue = new ArrayDeque<>();
        List<Node> melt = new ArrayList<>();
        boolean [][] visited = new boolean[R][C];

        queue.add(new Node(0,0));
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Node curNode = queue.poll();

            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];

                if(!inRange(nx,ny) || visited[nx][ny]) continue;
                visited[nx][ny] = true;

                if(map[nx][ny] == CHEESE) melt.add(new Node(nx,ny));
                else queue.add(new Node(nx,ny));
            }
        }

        for(Node target : melt){
            map[target.x][target.y] = BLANK;
        }

        return melt.size();
    }

    private static boolean isCheeseRemains(){
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
