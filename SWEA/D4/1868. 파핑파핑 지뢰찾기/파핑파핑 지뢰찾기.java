import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] dx = {-1,-1,-1,0,0,1,1,1};
    static int [] dy = {-1,0,1,-1,1,-1,0,1};
    static int N;

    static char [][] graph;
    static int [][] bombCnt;
    static boolean [][] visited;

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int answer = 0;
            N = Integer.parseInt(br.readLine());
            graph = new char[N][N];
            visited = new boolean[N][N];
            bombCnt = new int[N][N];

            for(int i=0;i<N;i++){
                String input = br.readLine();
                for(int j=0;j<N;j++){
                    graph[i][j] = input.charAt(j);
                }
            }

            //주변 폭탄 개수 세기
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(graph[i][j] != '*') countBomb(i,j);
                }
            }

            //주변에 폭탄이 없는 경우 먼저 클릭
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(bombCnt[i][j] == 0 && graph[i][j] != '*' && !visited[i][j]){
                        answer++;
                        click(i,j);
                    }
                }
            }

            //아직 클릭하지 않은 부분 클릭
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!visited[i][j] && graph[i][j] != '*') answer++;
                }
            }

            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void click(int x,int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            for(int i=0;i<8;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(!inRange(nx,ny) || visited[nx][ny]) continue;
                visited[nx][ny] = true;

                if(bombCnt[nx][ny] == 0 && graph[nx][ny] != '*'){
                    q.add(new Node(nx,ny));
                }
            }
        }
    }
    static void countBomb(int x,int y){
        int cnt = 0;
        for(int i=0;i<8;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!inRange(nx,ny)) continue;
            if(graph[nx][ny] == '*') cnt++;
        }
        bombCnt[x][y] = cnt;
    }

    static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >=0 && y < N;
    }

    static void debug(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(bombCnt[i][j] + " ");
            }
            System.out.println();
        }
    }
}
