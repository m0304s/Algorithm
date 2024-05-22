import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int [][] map;
    static boolean [][] visited;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};

    static class Node{
        int x;
        int y;
        int dept;
        public Node(int x,int y, int dept){
            this.x = x;
            this.y = y;
            this.dept = dept;
        }
        public Node(){

        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        public int getDept(){
            return dept;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = (int)line.charAt(j)-'0';
            }
        }
        
        Node answer = bfs(0,0);
        bw.write(answer.getDept()+"\n");
        bw.flush();
    }
    public static Node bfs(int x,int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 1));
        Node answer = new Node();
        while (!q.isEmpty()) {
            Node now = q.poll();
            if(now.getX()==(N-1) && now.getY() == (M-1)){
                answer = now;
                break;
            }
            for(int i=0;i<4;i++){
                int nextX = now.getX()+dx[i];
                int nextY = now.getY()+dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M){
                    continue;
                }
                if(map[nextX][nextY] == 0 || visited[nextX][nextY]){
                    continue;
                }
                q.offer(new Node(nextX, nextY, now.getDept()+1));
                visited[nextX][nextY] = true;
            }
        }
        return answer;
    }
}
