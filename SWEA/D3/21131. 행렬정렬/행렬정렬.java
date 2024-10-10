import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int size = Integer.parseInt(br.readLine());
            int [][] map = new int[size][size];
            boolean [] check = new boolean[size];
            //행렬 입력
            for(int i=0;i<size;i++){
                String [] tokens = br.readLine().split(" ");
                for(int j=0;j<size;j++){
                    map[i][j] = Integer.parseInt(tokens[j]);
                }
            }
            for(int i=0;i<size;i++){
                check[i] = map[0][i] == (i+1);
            }

            int count = 0;
            for(int i=size-1;i>0;i--){
                if(!check[i]){
                    count++;
                    for(int j=0;j<i;j++){
                        check[j] = !check[j];
                    }
                }
            }
            bw.write(count+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
