import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1, 1};

    static int [][] map;
    static int [][] dist;
    static int N;

    static class Node implements Comparable<Node>{
        int x,y;
        int distance;

        public Node(int x,int y,int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int compareTo(Node o){
            return this.distance - o.distance;
        }
    }
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dist = new int[N][N];

            for(int i=0;i<N;i++){
                String input = br.readLine();
                for(int j=0;j<N;j++){
                    map[i][j] = input.charAt(j) - '0';
                }
            }

            for (int[] ints : dist) {
                Arrays.fill(ints,Integer.MAX_VALUE);
            }
            dist[0][0] = 0;
            dijkstra();
            bw.write("#"+t+" "+dist[N-1][N-1]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(!inRange(nx,ny)) continue;

                int nd = cur.distance + map[nx][ny];

                if(dist[nx][ny] > nd){
                    dist[nx][ny] = nd;
                    pq.add(new Node(nx,ny,nd));
                }
            }
        }
    }
    private static boolean inRange(int x,int y){
        return x>=0 && x < map.length && y >= 0 && y < map.length;
    }
}
