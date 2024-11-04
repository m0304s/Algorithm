import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int PR,PC;
    static int N,M;
    static char [][] map;

    // 상 하 좌 우
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    static class Simulate implements Comparable<Simulate>{
        String direction;
        int time;

        public Simulate(String direction, int time){
            this.direction = direction;
            this.time = time;
        }
        public int compareTo(Simulate o){
            if(this.time == o.time){
                //시간이 같은 방향이 여러가지라면, URDL 순으로 정렬
                return o.direction.compareTo(this.direction);
            }
            return o.time - this.time;
        }
    }

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        map = new char[N+1][M+1];

        // 우주 입력
        for(int i=0;i<N;i++){
            String input = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = input.charAt(j);
            }
        }

        tokens = br.readLine().split(" ");
        PR = Integer.parseInt(tokens[0]) - 1;
        PC = Integer.parseInt(tokens[1]) - 1;

        ArrayList<Simulate> result = new ArrayList<>();

        for(int i=0;i<4;i++){
            if(i == 0){
                Simulate u = new Simulate("U", simulate(PR, PC, i));
                result.add(u);
            }
            if(i == 1){
                Simulate d = new Simulate("D", simulate(PR, PC, i));
                result.add(d);
            }
            if(i == 2){
                Simulate l = new Simulate("L", simulate(PR, PC, i));
                result.add(l);
            }
            if(i == 3){
                Simulate r = new Simulate("R", simulate(PR, PC, i));
                result.add(r);
            }
        }
        Collections.sort(result);
        if(result.get(0).time == Integer.MAX_VALUE){
            bw.write(result.get(0).direction+"\n"+"Voyager\n");
        }else{
            bw.write(result.get(0).direction+"\n"+result.get(0).time+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int simulate(int x,int y,int dir){
        int time = 0;
        boolean [][][] visited = new boolean[N][M][4];

        while (true){
            x += dx[dir];
            y += dy[dir];
            time++;

            // Check bounds
            if (x < 0 || x >= N || y < 0 || y >= M) {
                return time;
            }
            if (map[x][y] == 'C') {
                return time;
            }

            // Check for loop
            if (visited[x][y][dir]) {
                return Integer.MAX_VALUE; // Infinite loop detected (Voyager case)
            }
            visited[x][y][dir] = true;

            if(map[x][y] == '/'){
                //상 하 좌 우
                if(dir == 3) dir = 0;
                else if(dir == 2) dir = 1;
                else if(dir == 1) dir = 2;
                else if(dir == 0) dir = 3;
            }else if(map[x][y] == '\\'){
                if(dir == 3) dir = 1;
                else if(dir == 2) dir = 0;
                else if(dir == 1) dir = 3;
                else if(dir == 0) dir = 2;
            }
        }
    }

    static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
