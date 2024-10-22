import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int [][] map;
    static boolean[][] visited;

    //상 하 좌 우
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Answer{
        int roomNum;
        int count;
        Node room;
        public Answer(int roomNum, int count ,Node room){
            this.roomNum = roomNum;
            this.count = count;
            this.room = room;
        }
        public String toString(){
            return "RoomNum : " + this.roomNum + " Count : " + this.count +" X : " + room.x + " Y : "+ room.y;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];

            List<Answer> answerList = new ArrayList<>();
            for(int i=0;i<N;i++){
                String [] tokens = br.readLine().split(" ");
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }
            //모든 경우에 대해 bfs 진행
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    fillVisitedFalse();
                    answerList.add(bfs(i,j));
                }
            }
            Collections.sort(answerList, new Comparator<Answer>() {
                @Override
                //방의 개수가 최대인 방이 여럿이라면 적힌 수가 가장 작은 것을 출력
                public int compare(Answer o1, Answer o2) {
                    if(o1.count == o2.count){
                        return o1.roomNum - o2.roomNum;
                    }else return o2.count - o1.count;
                }
            });
            Answer answer = answerList.get(0);
            bw.write("#"+t+" "+ answer.roomNum + " "+answer.count+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static Answer bfs(int x,int y){
        int cnt = 0;
        Queue<Node> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Node(x,y));

        while(!q.isEmpty()){
            Node cur = q.poll();
            cnt++;
            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(!inRange(nx,ny)) continue;
                if(visited[nx][ny]) continue;

                if(map[nx][ny] == map[cur.x][cur.y]+1){
                    visited[nx][ny] = true;
                    q.add(new Node(nx,ny));
                }
            }
        }
        return new Answer(map[x][y],cnt, new Node(x,y));
    }

    static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    static void fillVisitedFalse(){
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans,false);
        }
    }
}
