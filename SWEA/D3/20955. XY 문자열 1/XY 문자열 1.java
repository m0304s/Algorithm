import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String answer = "";
            String s = br.readLine();
            String e = br.readLine();
            answer = checkPossible(s,e);
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static String checkPossible(String S, String E){
        for(int i=E.length();i>S.length();i--){
            boolean isLastY = E.endsWith("Y");
            E = E.substring(0,E.length()-1);
            if(isLastY) E = new StringBuilder(E).reverse().toString();
        }
        if(E.equals(S)) return "Yes";
        return "No";
    }
}
