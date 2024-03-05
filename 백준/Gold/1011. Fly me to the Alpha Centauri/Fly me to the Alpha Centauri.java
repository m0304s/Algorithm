import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        
        for(int i=0;i<N;i++){
            String [] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            bw.write(Integer.toString(solution(x,y))+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static int solution(int x,int y){
        int dist = y-x;
        if(dist==1){
            return 1;
        }else if(dist == 2){
            return 2;
        }else if(dist==3){
        }
        if(dist<=4){
            return 3;
        }
        int n = (int)Math.sqrt(dist);
        if(n*n == dist){
            return 2 * n - 1;
        }else if(dist<= n * (n+1)){
            return 2 * n;
        }else{
            return 2 * n + 1;
        }
    }
}

// 규칙 : 0 1 2 2 3 3 3 4 4 4 4... 

// 3
// 0 3
// 1 5
// 45 50
