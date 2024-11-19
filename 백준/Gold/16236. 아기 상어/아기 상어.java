import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int [][] ocean;

    //상 하 좌 우
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static class Shark{
        int x,y,size,countToEat;

        public Shark(int x,int y){
            this.x = x;
            this.y = y;
            this.size = 2;
            this.countToEat = 0;
        }

        public void eat(){
            countToEat++;
            if(countToEat == size){
                size++;
                countToEat = 0;
            }
        }
    }

    static Shark shark;

    static class Node{
        int x,y,distance;

        public Node(int x,int y,int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        ocean = new int[N][N];

        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                ocean[i][j] = Integer.parseInt(tokens[j]);
                if (ocean[i][j] == 9) {
                    shark = new Shark(i, j);
                    ocean[i][j] = 0; // 초기 위치 값 제거
                }
            }
        }
        int time = simulation();
        System.out.println(time);
    }

    private static int simulation(){
        int time = 0;
        while (true){
            Node fishToEat = findClosestFish();
            if(fishToEat == null){
                break;
            }
            time+=fishToEat.distance;
            shark.x = fishToEat.x;
            shark.y = fishToEat.y;
            ocean[shark.x][shark.y] = 0;
            shark.eat();
        }
        return time;
    }

    private static Node findClosestFish() {
        Node fishToEat = null;
        int minDistance = Integer.MAX_VALUE;
        int startX = shark.x;
        int startY = shark.y;

        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        visited[startX][startY] = true;
        queue.add(new Node(startX,startY,0));

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(!inRange(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                if(ocean[nx][ny] > shark.size) continue;

                visited[nx][ny] = true;
                int newDistance = cur.distance + 1;

                if(ocean[nx][ny] > 0 && ocean[nx][ny] < shark.size){
                    if (fishToEat == null ||
                            newDistance < minDistance ||
                            (newDistance == minDistance && (nx < fishToEat.x || (nx == fishToEat.x && ny < fishToEat.y)))
                    ) {
                        minDistance = newDistance;
                        fishToEat = new Node(nx, ny, newDistance);
                    }
                }else{
                    queue.add(new Node(nx,ny,newDistance));
                }
            }
        }
        return fishToEat;
    }


    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
