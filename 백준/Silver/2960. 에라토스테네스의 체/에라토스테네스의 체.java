import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]);
        int K = Integer.parseInt(token[1]);

        int count=0;
        Boolean[] array = new Boolean[N+1];
        
        for(int i=2;i<=N;i++){
            array[i]=true;
        }

        for(int i=2;i<=N;i++){
            for(int j=i;j<=N;j+=i){
                if(!array[j]){
                    continue;
                }
                count++;
                array[j]=false;
                if(count==K){
                    System.out.println(j);
                    System.exit(0);
                }
            }
        }
    }
}
