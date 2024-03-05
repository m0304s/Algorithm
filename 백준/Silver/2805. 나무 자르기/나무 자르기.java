import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        String[] treeString = br.readLine().split(" ");
        int [] trees = new int[N];
        int min = 0;
        int max = 0;
        for(int i=0;i<N;i++){
            trees[i]=Integer.parseInt(treeString[i]);
            if(max<trees[i]){
                max = trees[i];
            }
        }

        while(min<max){
            int mid = (min+max)/2;
            long sum = 0;
            for (int i : trees) {
                if(i-mid>0){
                    sum+=(i-mid);
                }
            }
            if(sum<M){
                max = mid;
            }else{
                min = mid+1;
            }
        }

        bw.write(Integer.toString(min-1)+"\n");
        bw.flush();
        bw.close();
    }
}
