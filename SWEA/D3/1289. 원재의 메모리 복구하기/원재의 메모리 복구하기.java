import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String originNum = br.readLine();
            String temp = "";
            for(int i=0;i<originNum.length();i++){
                temp+="0";
            }
            int answer = getMinCnt(temp,originNum);
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int getMinCnt(String start, String target){
        int cnt = 0;
        while(!start.equals(target)){
            //다른 문자열을 갖는 인덱스 찾기
            int targetIndex = findNotSame(start,target);
            String changeString = "";
            if(start.charAt(targetIndex) == '0') changeString = "1";
            if(start.charAt(targetIndex) == '1') changeString = "0";
            start = fillWith(start,targetIndex,changeString);
            cnt++;
        }
        return cnt;
    }
    static int findNotSame(String start, String target){
        for(int i=0;i<start.length();i++){
            if(start.charAt(i) != target.charAt(i)) return i;
        }
        return target.length();
    }
    static String fillWith(String string, int startIndex, String fillString){
        String temp = string.substring(0,startIndex);
        StringBuilder sb = new StringBuilder(temp);
        for(int i=startIndex;i<string.length();i++){
            sb.append(fillString);
        }
        return sb.toString();
    }
}
