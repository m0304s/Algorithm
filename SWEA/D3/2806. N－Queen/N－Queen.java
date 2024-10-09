import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int count = 0;
    static int N;
    //arr의 인덱스는 열을 가리킴, 값은 행을 가리킴
    static int [] arr;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            nqueen(0);
            bw.write("#"+t+" " + count+"\n");
            count = 0;
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void nqueen(int depth){
        if(depth == N){
            count++;
            return;
        }

        for(int i=0;i<N;i++){
            arr[depth] = i;

            if(possible(depth)){
                nqueen(depth+1);
            }
        }
    }
    static boolean possible(int column){
        for(int i=0;i<column;i++){
            if(arr[column] == arr[i]) return false;
            
            if(Math.abs(column - i) == Math.abs(arr[column] - arr[i])) return false;
        }
        return true;
    }
}
