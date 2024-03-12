import java.io.*;

public class Main {
    public static int [] color = new int[2];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [][] map = new int[N][N];

        for(int i=0;i<N;i++){
            String[] line = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        divide(N,0,0, map);
        bw.write(Integer.toString(color[0])+"\n"+Integer.toString(color[1])+"\n");
        bw.flush();
        bw.close();
    }
    public static void divide(int n, int r, int c, int [][]map){
        if(isAllNumbersSame(r,c,n,map)){
            color[map[r][c]]++;
        }else{
            int length = n/2;
            for(int i=0;i<2;i++){
                for(int j=0;j<2;j++){
                    divide(length, r+i*length, c+j*length, map);
                }
            }
        }
    }
    public static boolean isAllNumbersSame(int r,int c, int length, int[][]map){
        int start = map[r][c];
        for(int i=r;i<r+length;i++){
            for(int j=c;j<c+length;j++){
                if(map[i][j]!=start){
                    return false;
                }
            }
        }
        return true;
    }
}
