import java.io.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        int min = Math.min(N,M);
        int [][] map = new int[N][M];
        
        for(int i=0;i<N;i++){
            String input = br.readLine();
            for(int j=0;j<input.length();j++){
                map[i][j] = Integer.parseInt(Character.toString(input.charAt(j)));
            }
        }

        while(min>1){
            for(int i=0;i<=N-min;i++){
                for(int j=0;j<=M-min;j++){
                    int num = map[i][j];
                    if(num == map[i][j+min-1] && num == map[i+min-1][j] && num == map[i+min-1][j+min-1]){
                        bw.write(min*min+"\n");
                        bw.close();
                        br.close();
                        return;
                    }
                }
            }
            min--;
        }

        bw.write(min*min+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
