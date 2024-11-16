import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,K;

    static class People implements Comparable<People>{
        int number;
        public People(int number){
            this.number = number;
        }
        public int compareTo(People o){
            return this.number - o.number;
        }
    }

    static List<People> notSubmit;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            K = Integer.parseInt(tokens[1]);

            notSubmit = new ArrayList<>();

            for(int i=1;i<=N;i++){
                notSubmit.add(new People(i));
            }

            tokens = br.readLine().split(" ");
            for(int i=0;i<K;i++){
                notSubmit.remove(findPeople(Integer.parseInt(tokens[i])));
            }

            Collections.sort(notSubmit);

            bw.write("#"+t+" ");
            for (People p : notSubmit) {
                bw.write(p.number+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findPeople(int number) {
        for(int i=0;i<notSubmit.size();i++){
            if(notSubmit.get(i).number == number) return i;
        }
        throw new IllegalStateException();
    }
}
