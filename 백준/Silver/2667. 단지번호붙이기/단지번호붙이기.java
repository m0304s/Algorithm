import java.io.*;
import java.util.*;

public class Main {
    static int [][] map;
    static int N, houseCnt=0;
    static boolean [][] visited;
    
    //상하좌우
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};

    static class Node{
        int x;
        int y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }

    public static void main(String[] args) throws IOException{
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = (int)line.charAt(j)-'0';
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    houseCnt=0;
                    bfs(i,j);
                    list.add(houseCnt);
                }
            }
        }
        Collections.sort(list);
        bw.write(list.size()+"\n");
        for(int i : list){
            bw.write(i+"\n");
        }
        bw.flush();
    }
    public static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
            houseCnt++;
            for(int i=0;i<4;i++){
                int nextX = now.getX() + dx[i];
                int nextY = now.getY() + dy[i];

                //다음 좌표가 MAP 범위를 벗어날 경우
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >=N){
                    continue;
                }
                // 이미 방문한 곳이거나, 집이 아닌 경우
                if(map[nextX][nextY] == 0 || visited[nextX][nextY]){
                    continue;
                }
                q.add(new Node(nextX, nextY));
                visited[nextX][nextY] = true;
            }
        }
    }
}
