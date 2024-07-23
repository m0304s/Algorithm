import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] nums = br.readLine().split(" ");
        int N = Integer.parseInt(nums[0]);
        int M = Integer.parseInt(nums[1]);

        Set<String> nSet = new HashSet<>();
        Set<String> nmSet = new HashSet<>();
        for(int i=0;i<N;i++){
            nSet.add(br.readLine());
        }
        for(int i=0;i<M;i++){
            String a = br.readLine();
            if(nSet.contains(a)){
                nmSet.add(a);
            }
        }
        bw.write(nmSet.size()+"\n");
        ArrayList<String> answer = new ArrayList<>();
        for (String string : nmSet) {
            answer.add(string);
        }
        Collections.sort(answer);
        for (String string : answer) {
            bw.write(string+"\n");
        }
        bw.flush();
    }
}
