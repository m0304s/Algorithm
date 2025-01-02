import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int RED = 3, GREEN = 4, FLOWER = 5;
    private static final int [] dx = {-1,1,0,0};
    private static final int [] dy = {0,0,-1,1};
    private static int row,col,green,red;
    private static int [] greens,reds;
    private static boolean [] visited;
    private static int [][] garden;
    private static List<Pos> possible;
    private static int max;

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        row = Integer.parseInt(tokens[0]);
        col = Integer.parseInt(tokens[1]);
        green = Integer.parseInt(tokens[2]);
        red = Integer.parseInt(tokens[3]);
        possible = new ArrayList<>();
        greens = new int[green];
        reds = new int[red];
        visited = new boolean[10];
        garden = new int[row][col];
        max = Integer.MIN_VALUE;
        for(int i=0;i<row;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<col;j++){
                garden[i][j] = Integer.parseInt(tokens[j]);
                if(garden[i][j] == 2) possible.add(new Pos(i,j));
            }
        }

        parm_green(0,0);
        bw.write(max+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void parm_green(int start, int r){
        if(r == green){
            parm_red(0,0);
            return;
        }

        for(int i = start;i<possible.size();i++){
            if(visited[i]) continue;

            visited[i] = true;
            greens[r] = i;
            parm_green(i+1,r+1);
            visited[i] = false;
        }
    }

    private static void parm_red(int start, int r){
        if(r == red){
            bfs();
            return;
        }

        for(int i = start;i<possible.size();i++){
            if(visited[i]) continue;

            visited[i] = true;
            reds[r] = i;
            parm_red(i+1,r+1);
            visited[i] = false;
        }
    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        Pair[][] state = new Pair[row][col];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
                state[i][j] = new Pair();
        }

        for(int i=0;i<greens.length;i++){
            Pos pos = possible.get(greens[i]);
            state[pos.x][pos.y] = new Pair(0,GREEN);
            queue.add(new Pos(pos.x,pos.y));
        }

        for(int i=0;i<reds.length;i++){
            Pos pos = possible.get(reds[i]);
            state[pos.x][pos.y] = new Pair(0,RED);
            queue.add(new Pos(pos.x,pos.y));
        }

        int sum = 0;
        while (!queue.isEmpty()){
            Pos cur = queue.poll();
            int curTime = state[cur.x][cur.y].time;
            int curType = state[cur.x][cur.y].type;

            if(state[cur.x][cur.y].type == FLOWER) continue;

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(inRange(nx,ny) && garden[nx][ny] != 0){
                    if(state[nx][ny].type == 0){
                        state[nx][ny] = new Pair(curTime+1,curType);
                        queue.offer(new Pos(nx,ny));
                    }else if(state[nx][ny].type == GREEN){
                        if(curType == RED && state[nx][ny].time == curTime+1){
                            state[nx][ny].type = FLOWER;
                            sum++;
                        }
                    }else if(state[nx][ny].type == RED){
                        if(curType == GREEN && state[nx][ny].time == curTime+1){
                            state[nx][ny].type = FLOWER;
                            sum++;
                        }
                    }
                }
            }
        }
        max = Math.max(max,sum);
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < row
                && y >= 0 && y < col;
    }

    private static class Pos{
        int x,y;

        public Pos(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    private static class Pair{
        int time,type;

        public Pair(int time, int type){
            this.time = time;
            this.type = type;
        }

        public Pair(){

        }
    }
}
