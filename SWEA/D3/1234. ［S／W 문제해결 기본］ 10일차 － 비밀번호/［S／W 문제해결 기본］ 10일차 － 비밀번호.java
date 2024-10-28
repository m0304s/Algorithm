import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int TEST_CASE = 10;
    public static void main(String[] args) throws IOException {
        for(int t=1;t<=TEST_CASE;t++){
            String [] tokens = br.readLine().split(" ");
            int testCase = Integer.parseInt(tokens[0]);
            String inputNum = tokens[1];
            String answer = removeCoupleNum(inputNum);
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static String removeCoupleNum(String numString){
        while(true){
            if(numString.length() == 1){
                return numString;
            }
            if(!hasCoupleNum(numString)){
                break;
            }
            for(int i=0;i<numString.length()-1;i++){
                if(numString.charAt(i) == numString.charAt(i+1)){
                    String firstSplit = numString.substring(0,i);
                    String secondSplit = numString.substring(i+2,numString.length());
                    numString = firstSplit+secondSplit;
                    break;
                }
            }
        }
        return numString;
    }
    static boolean hasCoupleNum(String input){
        if(input.length() <=1){
            return false;
        }else{
            for(int i=0;i<input.length()-1;i++){
                if(input.charAt(i) == input.charAt(i+1)) return true;
            }
        }
        return false;
    }
}
