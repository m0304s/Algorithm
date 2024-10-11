import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for(int i=1;i<=N;i++){
            String num = checkNum(Integer.toString(i));
            bw.write(num);
            if(i!=N) bw.write(" ");
        }
        bw.flush();
    }
    static String checkNum(String numString){
        String answer = "";
        for(int i=0;i<numString.length();i++){
            if(numString.charAt(i) == '3' || numString.charAt(i) == '6' || numString.charAt(i) == '9'){
                answer += "-";
            }else{
                answer+=Character.toString(numString.charAt(i));
            }
        }
        if (answer.contains("-")) {
            answer = answer.replaceAll("[0-9]", "");
        }

        return answer;
    }
}
