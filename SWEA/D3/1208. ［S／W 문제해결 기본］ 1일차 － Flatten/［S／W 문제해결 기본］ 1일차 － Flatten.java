import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            int dumpCount = Integer.parseInt(br.readLine());
            String [] tokens = br.readLine().split(" ");
            List<Integer> boxList = new ArrayList<>();
            for(int i=0;i<tokens.length;i++){
                boxList.add(Integer.parseInt(tokens[i]));
            }
            while(dumpCount-- >0){
                Collections.sort(boxList);
                boxList.set(0,boxList.get(0)+1);
                boxList.set(boxList.size()-1,boxList.get(boxList.size()-1)-1);
            }
            Collections.sort(boxList);
            int answer = boxList.get(boxList.size()-1)-boxList.get(0);
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
