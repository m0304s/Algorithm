import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int VISITED = 1, NOT_VISITED = 0, FINISHED = 2;
    static int [] students;
    static int [] visited;
    static int N, teamMemberCount;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            teamMemberCount = 0;
            N = Integer.parseInt(br.readLine());    //학생의 수
            students = new int[N+1];
            visited = new int[N+1];
            String [] tokens = br.readLine().split(" ");
            for(int i=1;i<=N;i++){
                students[i] = Integer.parseInt(tokens[i-1]);
            }

            //학생들에 대해 DFS 탐색
            for(int i=1;i<=N;i++){
                if(visited[i] == NOT_VISITED){
                    dfs(i);
                }
            }

            bw.write(N-teamMemberCount+"\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    static void dfs(int number){
        visited[number] = VISITED;

        int next = students[number];

        if(visited[next] == NOT_VISITED){
            dfs(next);
        }else if(visited[next] == VISITED){
            //사이클 발견 -> 사이클에 포함된 팀원 수 계산
            int current = next;

            while(true){
                teamMemberCount++;
                current = students[current];
                if(current == next) break;
            }
        }

        visited[number] = FINISHED;
    }
}
