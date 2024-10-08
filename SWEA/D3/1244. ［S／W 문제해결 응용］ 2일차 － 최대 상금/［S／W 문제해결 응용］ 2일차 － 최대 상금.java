import java.util.*;
import java.io.*;
class Solution {
    static char[] nums;
    static int limitCount;
    static int result;
	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++){
            nums = sc.next().toCharArray();
            limitCount = sc.nextInt();
            result = 0;
            dfs(0,0);
            System.out.println("#" + tc + " " + result);
		}
	}
    private static void dfs(int pos,int count){
        if(count == limitCount){
            int value = Integer.parseInt(new String(nums));
            result = Math.max(result,value);
            return;
        }

        for(int i = pos; i < nums.length; i++){
            for(int j =i + 1; j < nums.length; j++){
            	swap(i,j);
                dfs(i,count+1);					                
                swap(i,j);
            }
        }
    }
    
    private static void swap(int i, int j){
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}