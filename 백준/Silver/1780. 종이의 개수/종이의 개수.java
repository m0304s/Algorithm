import java.io.*;

public class Main {
    public static int[] color = new int[3]; //색상별 종이의 개수를 저장할 배열
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [][] paper = new int[N][N];

        for(int i=0;i<N;i++){
            String [] lines = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                paper[i][j] = Integer.parseInt(lines[j]);
            }
        }
        divide(N, 0, 0, paper);
        bw.write(Integer.toString(color[0])+"\n");
        bw.write(Integer.toString(color[1])+"\n");
        bw.write(Integer.toString(color[2])+"\n");
        bw.flush();
        bw.close();
    }
    public static void divide(int n, int r,int c, int [][]paper){
        if(isAllNumbersSame(n,r,c,paper)){
            color[paper[r][c]+1]++;
        }else{
            int length = n/3;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    divide(length, r+i*length, c+j*length, paper);
                }
            }
        }
    }
    public static boolean isAllNumbersSame(int length,int r,int c,int [][] paper){
        int start = paper[r][c];
        for(int i=r;i<r+length;i++){
            for(int j=c;j<c+length;j++){
                if(paper[i][j]!=start){
                    return false;
                }
            }
        }
        return true;
    }
}
