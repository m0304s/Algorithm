import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M;
    static char [][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; //0, 1, 2, 3 (상, 우, 하, 좌) - 시계 방향

    static boolean [][][][] visited;

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Marble{
        Node red;
        Node blue;
        int count;

        public Marble(Node red, Node blue, int count){
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    static Node red,blue;

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for(int i=0;i<N;i++){
            String input = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'R'){
                    red = new Node(i,j);
                }else if(map[i][j] == 'B'){
                    blue = new Node(i,j);
                }
            }
        }

        bw.write(bfs()+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    private static int bfs(){
        Queue<Marble> queue = new LinkedList<>();
        queue.add(new Marble(red,blue,1));
        visited[red.x][red.y][blue.x][blue.y] = true;

        while(!queue.isEmpty()){
            Marble cur = queue.poll();
            int curRx = cur.red.x;
            int curRy = cur.red.y;
            int curBx = cur.blue.x;
            int curBy = cur.blue.y;

            if(cur.count > 10) return -1;

            for(int i=0;i<4;i++){
                int newRx = curRx;
                int newRy = curRy;
                int newBx = curBx;
                int newBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                //빨간색 공 이동 -> 벽을 만날때까지
                while (inRange(newRx+dx[i],newRy+dy[i]) && map[newRx + dx[i]][newRy + dy[i]] != '#'){
                    newRx+=dx[i];
                    newRy+=dy[i];

                    //이동 중 구멍을 만날 경우
                    if(map[newRx][newRy] == 'O'){
                        isRedHole = true;
                        break;
                    }
                }

                //파란색 공 이동 -> 벽을 만날때까지
                while(inRange(newBx+dx[i], newBy+dy[i]) && map[newBx+dx[i]][newBy+dy[i]] != '#'){
                    newBx+=dx[i];
                    newBy+=dy[i];

                    if(map[newBx][newBy] == 'O'){
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) continue;
                if(isRedHole && !isBlueHole){
                    return cur.count;
                }

                //이동한 결과 공이 겹칠때 -> 위치 보정
                //0, 1, 2, 3 (상, 우, 하, 좌) - 시계 방향
                if(newRx == newBx && newRy == newBy){
                    if(i == 0){ //위로 기울이 경우 -> 더 큰 x값을 가지는 구슬이 뒤에 위치
                        if(curRx > curBx) newRx-=dx[i];
                        else newBx-=dx[i];
                    } else if(i == 1) { // 오른쪽으로 기울이기
                        // 더 작은 y값을 가지는 구슬이 뒤로 감
                        if(curRy < curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }else if(i == 2){
                        // 더 작은 x값을 가지는 구슬이 뒤로 감
                        if(curRx < curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }else if(i == 3){   //왼쪽으로 기울일 경우 -> 더 큰 y값을 가지는 구슬이 뒤에 위치
                        if(curRy > curBy) newRy-=dy[i];
                        else newBy-=dy[i];
                    }
                }

                if(!visited[newRx][newRy][newBx][newBy]){
                    queue.add(new Marble(new Node(newRx,newRy),new Node(newBx,newBy), cur.count+1));
                    visited[newRx][newRy][newBx][newBy] = true;
                }
            }
        }
        return -1;
    }
    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
