import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int K;   //트리의 깊이
    private static StringBuffer [] ans;
    private static int [] arr;
    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        String [] tokens = br.readLine().split(" ");
        ans = new StringBuffer[K];
        arr = new int[(int) Math.pow(2, K) - 1];
        for(int i=0;i<K;i++){
            ans[i] = new StringBuffer();
        }

        for(int i=0;i<tokens.length;i++){
            arr[i] = Integer.parseInt(tokens[i]);
        }

        search(0,arr.length-1,0);

        for(int i=0;i<K;i++){
            bw.write(ans[i].toString()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void search(int start, int end, int depth){
        if(depth == K) return;
        int mid = (start+end)/2;
        ans[depth].append(arr[mid]+" ");

        search(start,mid-1,depth+1);
        search(mid+1,end,depth+1);
    }
}
