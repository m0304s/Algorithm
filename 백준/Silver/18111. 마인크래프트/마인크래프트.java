import java.io.*;

public class Main{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);    //세로
        int m = Integer.parseInt(tokens[1]);    //가로
        int b = Integer.parseInt(tokens[2]);

        int [][] map = new int[n][m];
        int min = 256;
        int max = 0;

        for(int i=0;i<n;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
                if(max<map[i][j]){
                    max = map[i][j];
                }
                if(min>map[i][j]){
                    min = map[i][j];
                }
            }
        }
        int time = Integer.MAX_VALUE;
        int height = 0;

        for(int i = min; i <= max ; i++){
            int count = 0;
            int block = b;

            for(int j=0;j<n;j++){
                for(int k=0;k<m;k++){
                    if(i < map[j][k]){
                        count += (map[j][k] - i) * 2;
                        block += (map[j][k] - i);
                    }else{
                        count += (i-map[j][k]);
                        block -= (i-map[j][k]);
                    }
                }
            }
            if(block < 0){
                break;
            }
            if(time>=count){
                time = count;
                height = i;
            }
        }
        bw.write(time+" "+height+"\n");
        bw.flush();
    }
}