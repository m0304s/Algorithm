import java.io.*;
import java.util.*;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int [] dx = {0,0,-1,1};
    static final int [] dy = {-1,1,0,0};
    static final int BLANK = 0, CHEESE = 1;

    static int R,C;
    static int [][] map;

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

        // 치즈가 남아있는 동안 반복
        while(checkCheeseRemains()){
            melt(); // 치즈 녹이기
            time++; // 시간 증가
        }
        bw.write(time+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void melt(){
        // 매 시간마다 외부 공기 영역을 다시 탐색 (BFS)
        Queue<Node> queue = new ArrayDeque<>();
        boolean [][] visitedAir = new boolean[R][C]; // 외부 공기 탐색을 위한 방문 배열
        List<Node> melts = new ArrayList<>(); // 이번 턴에 녹을 치즈 리스트

        // (0,0)은 항상 외부 공기라고 가정하고 BFS 시작
        queue.add(new Node(0,0));
        visitedAir[0][0] = true;

        while(!queue.isEmpty()){
            Node curNode = queue.poll();

            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];

                if(!inRange(nx,ny) || visitedAir[nx][ny]) continue;

                if(map[nx][ny] == BLANK){ // 인접한 칸이 공기라면 외부 공기 영역 계속 탐색
                    visitedAir[nx][ny] = true;
                    queue.add(new Node(nx,ny));
                }
            }
        }

        // 모든 치즈 칸을 돌면서 외부 공기와 2칸 이상 접촉하는지 확인
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == CHEESE){
                    int externalAirContactCount = 0;
                    for(int d=0; d<4; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(inRange(nx, ny) && visitedAir[nx][ny]){ // 인접 칸이 범위 내에 있고, 외부 공기라면
                            externalAirContactCount++;
                        }
                    }
                    if(externalAirContactCount >= 2){ // 외부 공기와 2칸 이상 접촉하면 녹을 치즈로 추가
                        melts.add(new Node(i,j));
                    }
                }
            }
        }

        // 녹을 치즈들을 실제로 녹이기
        for(Node node : melts){
            map[node.x][node.y] = BLANK;
        }
    }

    // 모든 치즈가 녹았는지 확인
    private static boolean checkCheeseRemains(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == CHEESE) return true;
            }
        }
        return false;
    }

    // 맵 범위 확인
    private static boolean inRange(int x,int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    // 좌표를 나타내는 Node 클래스
    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}