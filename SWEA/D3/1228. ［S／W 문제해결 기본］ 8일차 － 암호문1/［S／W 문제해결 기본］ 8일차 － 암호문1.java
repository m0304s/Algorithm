import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, commandCount;
    static List<Integer> numList;
    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            N = Integer.parseInt(br.readLine());
            numList = new ArrayList<>();
            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                numList.add(Integer.parseInt(tokens[i]));
            }
            commandCount = Integer.parseInt(br.readLine());
            String commandLine = br.readLine();
            tokens = commandLine.split(" ");
            for(int i=0;i<tokens.length;i++){
                String command = tokens[i];
                int x = Integer.parseInt(tokens[i+1]);
                int y = Integer.parseInt(tokens[i+2]);
                List<Integer> addNum = new ArrayList<>();
                for(int j=0;j<y;j++){
                    addNum.add(Integer.parseInt(tokens[i+3+j]));
                }
                // 특정 인덱스에 숫자 추가
                numList.addAll(x,addNum);
                i+=y+2;
            }
            bw.write("#"+t+" ");
            int printCount = 0;
            for(int i=0;i<numList.size();i++){
                if(printCount == 10){
                    break;
                }
                bw.write(numList.get(i)+" ");
                printCount++;
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
