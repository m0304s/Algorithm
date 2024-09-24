import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int M;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static char [][] map;

    static class Node{
        int x;
        int y;
        int distance;
        boolean destroyed;

        public Node(int x, int y, int distance, boolean destroyed){
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.destroyed = destroyed;
        }
    }
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        map = new char[N][M];
        boolean[][][] visited = new boolean[N][M][2];

        for(int i=0;i<N;i++){
            String input = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = input.charAt(j);
            }
        }

        bfs(visited);
        bw.flush();
    }
    static void bfs(boolean [][][] visited) throws IOException{
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0,1,false));

        while(!queue.isEmpty()){
            Node now = queue.poll();

            if(now.x == N-1 && now.y == M-1){
                bw.write(now.distance + "\n");
                return;
            }

            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;

                if(map[nx][ny] == '0'){   //벽이 아니라면
                    if(!now.destroyed && !visited[nx][ny][0]){   //한번도 벽을 부수지 않았다면
                        queue.add(new Node(nx, ny, now.distance+1, false));
                        visited[nx][ny][0] = true;
                    }else if(now.destroyed && !visited[nx][ny][1]){
                        queue.add(new Node(nx, ny, now.distance+1, true));
                        visited[nx][ny][1] = true;
                    }
                }else if(map[nx][ny] == '1'){  //만약 벽이라면
                    if(!now.destroyed){ //벽을 기존에 부수지 않았더라면...
                        queue.add(new Node(nx, ny, now.distance+1, true));
                        visited[nx][ny][1] = true;
                    }   //벽을 기존에 부쉈다면 아무것도 할 수 없음
                }
            }
        }
        bw.write("-1\n");
    }
}
