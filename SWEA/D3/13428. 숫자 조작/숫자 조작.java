import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int minValue;
    static int maxValue;
    static String inputValue;
    static int N;

    static int [] output;
    static int [] array;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            inputValue = br.readLine();
            N = Integer.parseInt(inputValue);
            minValue = Integer.MAX_VALUE;
            maxValue = Integer.MIN_VALUE;
            output = new int[2];
            array = new int[inputValue.length()];
            for(int i=0;i<array.length;i++){
                array[i] = i;
            }
            combination(0,0);
            minValue = Math.min(N,minValue);
            maxValue = Math.max(N,maxValue);
            bw.write("#"+t+" "+minValue+" "+maxValue+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int depth,int index){
        //한 쌍의 숫자 고르기
        if(depth == 2){
            int changedNum = getChangedNum();
            if(String.valueOf(changedNum).length() != inputValue.length()) return;
            minValue = Math.min(minValue,changedNum);
            maxValue = Math.max(maxValue,changedNum);
            return;
        }
        if(index >= inputValue.length()) return;

        output[depth] = array[index];
        combination(depth+1,index+1);
        combination(depth,index+1);
    }

    private static int getChangedNum() {
        char [] chars = inputValue.toCharArray();
        char temp = chars[output[0]];
        chars[output[0]] = chars[output[1]];
        chars[output[1]] = temp;
        return Integer.parseInt(new String(chars));
    }
}
