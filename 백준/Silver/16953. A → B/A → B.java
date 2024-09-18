import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Long startNum;
    static Long targetNum;
    static Set<Long> visited = new HashSet<>();
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        startNum = Long.parseLong(tokens[0]);
        targetNum = Long.parseLong(tokens[1]);
        bfs();
        bw.flush();
    }
    static void bfs() throws IOException{
        Queue<Long> queue = new LinkedList<>();
        queue.add(startNum);
        visited.add(startNum);

        int answer = 0;
        while(!queue.isEmpty()){
            answer++;
            int size = queue.size();
            for(int i=0;i<size;i++){
                Long nowNum = queue.poll();
                if(nowNum.equals(targetNum)){
                    System.out.println(answer);
                    return;
                }
                if(nowNum*2<=targetNum && !visited.contains(nowNum*2)){
                    queue.add(nowNum*2);
                    visited.add(nowNum*2);
                }
                if((nowNum*10+1) <= targetNum && !visited.contains(nowNum*10+1)){
                    queue.add(nowNum*10+1);
                    visited.add(nowNum*10+1);
                }
            }
        }
        System.out.println(-1);
    }
}
