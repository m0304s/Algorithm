import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int K = Integer.parseInt(tokens[1]);
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);

        int answer=0;
        for(int i=list.size()-1;i>=0;i--){
            if(list.get(i)>K){
                continue;
            }else{
                while(K>=list.get(i)){
                    answer++;
                    K-=list.get(i);
                }
                if(K<0){
                    break;
                }
            }
        }
        bw.write(Integer.toString(answer)+"\n");
        bw.flush();
        bw.close();
    }
}

/**
 * 4
 * 1
 * 2
 * 1
 * 4
 */