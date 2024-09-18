import java.io.*;;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [][] map = new int[N][N];
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<tokens.length;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int answer = map[0][0];
        for(int i=1;i<N;i++){
            for(int j=0;j<map[i].length;j++){
                if(j==0){
                    map[i][j] = map[i-1][j] + map[i][j];
                }else if(j==i){
                    map[i][j] = map[i-1][j-1] + map[i][j];
                }else{
                    map[i][j] = map[i][j] + Math.max(map[i-1][j], map[i-1][j-1]);
                }

                answer = Math.max(answer, map[i][j]);
            }
        }
        bw.write(answer+"\n");
        bw.flush();
    }
}
