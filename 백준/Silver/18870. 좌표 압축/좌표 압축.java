import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String [] tokens = br.readLine().split(" ");
        ArrayList<Integer> original = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        for (String string : tokens) {
            int inputNum = Integer.parseInt(string);
            original.add(inputNum);
            sorted.add(inputNum);
        }
        Collections.sort(sorted);

        int rank = 0;
        HashMap<Integer,Integer> answerMap = new HashMap<>();
        for (Integer integer : sorted) {
            if(!answerMap.containsKey(integer)){
                answerMap.put(integer, rank);
                rank++;
            }
        }
        for (Integer integer : original) {
            bw.write(answerMap.get(integer)+" ");
        }bw.write("\n");
        bw.flush();
    }
}
