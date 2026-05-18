import java.util.*;

class Solution {
    static int answer;
    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        int m = ans.length;
        HashSet<Integer>[] qSets = new HashSet[m];
        for(int i=0;i<m;i++){
            qSets[i] = new HashSet<>();
            for(int num : q[i]){
                qSets[i].add(num);   
            }
        }
        
        int [] currentComb = new int[5];
        makeCombination(1,0,n,currentComb,qSets,ans);
        
        return answer;
    }
    
    static void makeCombination(int start, int depth, int n, int [] currentComb, HashSet<Integer>[] qSets, int [] ans){
        if(depth == 5){
            if(isValidCode(currentComb, qSets, ans)){
                answer++;
            }
            return;
        }
        
        for(int i=start; i<=n; i++){
            currentComb[depth] = i;
            makeCombination(i+1, depth+1, n, currentComb, qSets, ans);
        }
    }
    
    static boolean isValidCode(int [] currentComb, HashSet<Integer>[] qSets, int [] ans){
        int m = qSets.length;
        
        for(int i=0;i<m;i++){
            int matchCount = 0;
            for(int num : currentComb){
                if(qSets[i].contains(num)) matchCount++;
            }
            
            if(matchCount != ans[i]){
                return false;
            }
        }
        
        return true;
    }
}