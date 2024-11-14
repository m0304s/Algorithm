import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Integer> answers;
    static Set<Integer> answerSet;
    static int [] numbers;
    static int [] output;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            answerSet = new HashSet<>();
            String [] tokens = br.readLine().split(" ");
            numbers = new int[tokens.length];
            output = new int[3];
            answers = new ArrayList<>();
            for(int i=0;i<tokens.length;i++){
                numbers[i] = Integer.parseInt(tokens[i]);
            }

            combination(0,0);
            answers.sort((a,b)-> b-a);
            bw.write("#"+t+" "+answers.get(4)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void combination(int depth, int index){
        if(depth == 3){
            int sum = 0;
            for (int i : output) {
                sum+=i;
            }
            if(!answerSet.contains(sum)){
                answers.add(sum);
                answerSet.add(sum);
            }
            return;
        }

        if(index >= numbers.length) return;

        output[depth] = numbers[index];
        combination(depth+1,index+1);
        combination(depth,index+1);
    }
}
