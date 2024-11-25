import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,L;
    static int [][] map;
    static int [] road;

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        L = Integer.parseInt(tokens[1]);
        map = new int[N][N];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int count = 0;
        //가로 형태의 길 중 지나갈 수 있는 길 체크
        for(int i=0;i<N;i++){
            road = map[i].clone();
            if(simulation(road)){
                count++;
            }
        }

        //세로 형태의 길 중 지나갈 수 있는 길 체크
        for(int i=0;i<N;i++){
            road = new int[N];
            for(int j=0;j<N;j++){
                road[j] = map[j][i];
            }
            if(simulation(road)){
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    private static boolean simulation(int [] road){
        boolean[] visited = new boolean[N];
        int index = 0;
        while(index < N-1){
            //높이 차이가 1 이상일 경우
            if(Math.abs(road[index] - road[index + 1]) > 1) return false;

            //경사로를 놓을 수 있는 경우
            if(road[index] < road[index + 1]){
                if(index - L + 1 < 0) return false;
                for(int i = index; i > index - L; i--){
                    if(road[i] != road[index] || visited[i]) return false;
                    visited[i] = true;
                }
            } else if(road[index] > road[index + 1]){
                if(index + L >= N) return false;
                for(int i = index + 1; i <= index + L; i++){
                    if(road[i] != road[index + 1] || visited[i]) return false;
                    visited[i] = true;
                }
            }
            index++;
        }
        return true;
    }
}
