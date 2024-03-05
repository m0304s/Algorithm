import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [][] price = new int[N+1][3];
        int [][] answer = new int[N+1][3];
        price[0][0]=price[0][1]=price[0][2]=answer[0][0]=answer[0][1]=answer[0][2]=0;
        for(int i=1;i<=N;i++){
            String[] token = br.readLine().split(" ");
            for(int j=0;j<token.length;j++){
                price[i][j] = Integer.parseInt(token[j]);
            }
        }
        for(int i=1;i<=N;i++){
            answer[i][0]=Math.min(answer[i-1][1],answer[i-1][2])+price[i][0];
            answer[i][1]=Math.min(answer[i-1][0],answer[i-1][2])+price[i][1];
            answer[i][2]=Math.min(answer[i-1][0],answer[i-1][1])+price[i][2];
        }
        int sum = Math.min(Math.min(answer[N][0],answer[N][1]),answer[N][2]);
        bw.write(Integer.toString(sum)+"\n");
        bw.flush();
        bw.close();
    }
}