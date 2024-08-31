import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Node{
        int x;
        int y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static int [] dx = {0,0,-1,1};
    static int [] dy = {-1,1,0,0};

    static int n,m;
    static String [][] map;
    static boolean [][] visited;
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);

        map = new String[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            String inpuString = br.readLine();
            for(int j=0;j<inpuString.length();j++){
                map[i][j] = Character.toString(inpuString.charAt(j));
                if(map[i][j].equals("I")){
                    q.add(new Node(i, j));
                }
            }
        }
        bfs();
        bw.flush();
    }
    public static void bfs() throws IOException{
        int people = 0; //도연이가 만날 수 있는 사람의 수
        while (!q.isEmpty()) {
            Node now = q.poll();
            if(map[now.x][now.y].equals("P")) people++;
            for(int i=0;i<4;i++){
                int newX = now.x+dx[i];
                int newY = now.y+dy[i];

                if (newX >= 0 && newX < n && newY >= 0 && newY < m && !visited[newX][newY] && (map[newX][newY].equals("O") || map[newX][newY].equals("P"))) {
                    visited[newX][newY] = true;
                    q.add(new Node(newX, newY));
                }
            }
        }
        if(people == 0){
            bw.write("TT\n");
        }else{
            bw.write(people+"\n");
        }
    }
}
