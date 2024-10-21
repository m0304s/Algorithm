import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int [] problems;

    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            problems = new int[N];
            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                problems[i] = Integer.parseInt(tokens[i]);
            }
            set.add(0);
            for(int i=0;i<N;i++){
                scoreSelect(problems[i]);
            }
            bw.write("#"+t+" "+set.size()+"\n");
            set.clear();
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void scoreSelect(int num) {
        Set<Integer> tmpSet = new HashSet<Integer>();
        tmpSet.addAll(set);
        Iterator<Integer> it = tmpSet.iterator();
        while(it.hasNext()) {
            set.add(it.next()+num);
        }
    }
}
