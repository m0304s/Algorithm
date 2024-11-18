import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,L,R;
    static int [][] map;
    static boolean [][] visited;

    //상 하 좌 우
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        L = Integer.parseInt(tokens[1]);
        R = Integer.parseInt(tokens[2]);
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int day = 0;
        while(true){
            visited = new boolean[N][N];
            boolean moved = false;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!visited[i][j]){
                        if(bfs(i,j)){   //연합을 생성하고 인구 이동
                            moved = true;
                        }
                    }
                }
            }

            if(!moved) break;

            day++;
        }
        System.out.println(day);
    }

    private static boolean bfs(int startX,int startY){
        Queue<Node> queue = new LinkedList<>();
        List<Node> union = new ArrayList<>();
        queue.add(new Node(startX,startY));
        union.add(new Node(startX,startY));
        visited[startX][startY] = true;

        int totalPopulation = map[startX][startY];
        while(!queue.isEmpty()){
            Node cur = queue.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(!inRange(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                if(!checkInRange(map[nx][ny],map[cur.x][cur.y])) continue;

                visited[nx][ny] = true;
                union.add(new Node(nx,ny));
                queue.add(new Node(nx,ny));
                totalPopulation+=map[nx][ny];
            }
        }
        //연합이 생성되었으면
        if(union.size() > 1){
            int avgPopulation = totalPopulation/union.size();
            for (Node node : union) {
                map[node.x][node.y] = avgPopulation;
            }
            return true;
        }
        //연합이 생성되지 않았다면
        return false;
    }


    private static boolean checkInRange(int i, int i1) {
        int difference = Math.abs(i-i1);
        return difference >= L && difference <= R;
    }

    private static boolean inRange(int x,int y){
        return x>=0 && x < N && y >= 0 && y < N;
    }
}
