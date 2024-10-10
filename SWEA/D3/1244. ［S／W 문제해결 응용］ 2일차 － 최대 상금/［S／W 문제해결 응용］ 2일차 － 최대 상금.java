import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static char[] nums;
    static int totalChance;
    static int answer;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            nums = tokens[0].toCharArray();
            totalChance = Integer.parseInt(tokens[1]);
            answer = 0;
            dfs(0,0);
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int pos, int chance){
        if(chance == totalChance){
            int value = Integer.parseInt(new String(nums));
            answer = Math.max(value,answer);
            return;
        }

        for(int i=pos;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                swap(i,j);
                dfs(i,chance+1);
                swap(i,j);
            }
        }
    }
    static void swap(int i,int j){
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
