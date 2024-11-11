import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int [][] ocean;
    static final int BABY_SHARK = 9;

    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static class Shark{
        int x,y,size,eatCount;

        public Shark(int x,int y){
            this.x = x;
            this.y = y;
            this.size = 2;
            this.eatCount = 0;
        }

        public void eat(){
            this.eatCount++;
            if(this.eatCount == this.size){
                eatCount = 0;
                increaseSize();
            }
        }

        public void increaseSize(){
            this.size++;
        }
    }

    static class Node{
        int x;
        int y;
        int distance;

        public Node(int x,int y,int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static Shark shark;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        ocean = new int[N][N];

        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                ocean[i][j] = Integer.parseInt(tokens[j]);
                if(ocean[i][j] == BABY_SHARK) {
                    shark = new Shark(i,j);
                    ocean[i][j] = 0;
                }
            }
        }

        bw.write(simulate()+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    private static int simulate(){
        int time = 0;

        while(true){
            Node fishToEat = findClosestFish();
            if(fishToEat == null) break;

            int distance = fishToEat.distance;
            time+=distance;

            shark.x = fishToEat.x;
            shark.y = fishToEat.y;

            shark.eat();
            ocean[shark.x][shark.y] = 0;
        }
        return time;
    }

    private static Node findClosestFish() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new Node(shark.x,shark.y,0));
        visited[shark.x][shark.y] = true;

        Node closestFish  = null;
        int minDistance = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            Node cur = queue.poll();

            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(!inRange(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                if(ocean[nx][ny] > shark.size) continue;

                visited[nx][ny] = true;
                int newDistance = cur.distance +1;

                //가장 가까운 물고기 선택
                if(ocean[nx][ny] > 0 && ocean[nx][ny] < shark.size){
                    if(newDistance < minDistance ||
                            (newDistance == minDistance && (nx < closestFish.x || (nx == closestFish.x && ny < closestFish.y)))) {
                        minDistance = newDistance;
                        closestFish = new Node(nx,ny,newDistance);
                    }
                }else{
                    queue.add(new Node(nx,ny,newDistance));
                }
            }
        }
        return closestFish;
    }

    private static boolean inRange(int x,int y){
        return x >=0 && x < N && y >= 0 && y < N;
    }
}
