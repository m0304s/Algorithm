import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int INF = 10000000;

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [][] graph = new int[N][N];

        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                graph[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if (graph[i][j] == 0) {
                    graph[i][j] = INF;
                }
            }
        }
        //플루이드-위샬 알고리즘 적용
        for(int i=0;i<N;i++){           //중간노드
            for(int j=0;j<N;j++){       //시작노드
                for(int k=0;k<N;k++){   //도착노드
                    if(graph[j][k] > graph[j][i] + graph[i][k]){
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(graph[i][j] == INF){
                    bw.write("0 ");
                }else{
                    bw.write("1 ");
                }
            }bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
