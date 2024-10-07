import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Character[][] candy;
    static int max = Integer.MIN_VALUE;
    static int N;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        candy = new Character[N][N];
        for(int i=0;i<N;i++){
            String input = br.readLine();
            for(int j=0; j<N;j++){
                candy[i][j] = input.charAt(j);
            }
        }

        //행을 기준으로 오른쪽 색상과 변경
        for(int i=0;i<N-1;i++){
            for(int j=0;j<N;j++){
                swap(i, j, i+1, j);
                search();
                swap(i+1,j,i,j);
            }
        }
        //열을 기준으로 아래쪽 색상과 변경
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
                swap(i,j,i,j+1);
                search();
                swap(i,j+1,i,j);
            }
        }
        bw.write(max+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void swap(int x1, int y1, int x2, int y2){
        Character temp = candy[x1][y1];
        candy[x1][y1] = candy[x2][y2];
        candy[x2][y2] = temp;
    }
    static void search(){
        //행에서 긴 수열 탐색
        for(int i=0;i<N;i++){
            int count = 1;
            for(int j=0;j<N-1;j++){
                if(candy[i][j] == candy[i][j+1]){
                    count++;
                    max = Math.max(count, max);
                }else{
                    count = 1;
                }
            }
        }

        //열에서 가장 긴 수열 탐색
        for(int i=0;i<N;i++){
            int count = 1;
            for(int j=0;j<N-1;j++){
                if(candy[j][i] == candy[j+1][i]){
                    count++;
                    max = Math.max(count, max);
                }else{
                    count = 1;
                }
            }
        }
    }
}
