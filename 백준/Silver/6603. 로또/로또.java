import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<Integer> numbers;
    static int [] output;
    static int K;

    public static void main(String[] args) throws IOException {
        while(true){
            String [] tokens = br.readLine().split(" ");
            if(Integer.parseInt(tokens[0]) == 0) break;
            K = Integer.parseInt(tokens[0]);
            numbers = new ArrayList<>();
            output = new int[6];
            for(int i=1;i<tokens.length;i++){
                numbers.add(Integer.parseInt(tokens[i]));
            }

            combination(0,0);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int depth, int index) throws IOException{
        if(depth == 6){
            String answer = "";
            for (int i : output) {
                answer+=i+" ";
            }
            bw.write(answer+"\n");
            return;
        }
        if(index >= K) return;
        output[depth] = numbers.get(index);
        combination(depth+1,index+1);
        combination(depth,index+1);
    }
}
