class Solution {
    public int solution(int[][] info, int n, int m) {
        int len = info.length;
        boolean[][][] dp = new boolean[len+1][n][m];
        
        dp[0][0][0] = true;
        
        for(int i=0;i<len;i++){
            int aTrace = info[i][0];
            int bTrace = info[i][1];
            
            for(int a=0;a<n;a++){
                for(int b=0;b<m;b++){
                    if(!dp[i][a][b]) continue;
                    
                    if(a + aTrace < n){
                        dp[i+1][a+aTrace][b] = true;
                    }
                    if(b + bTrace < m){
                        dp[i+1][a][b+bTrace] = true;
                    }
                }
            }
        }
        
        for(int a=0;a<n;a++){
            for(int b=0;b<m;b++){
                if(dp[len][a][b]){
                    return a;
                }
            }
        }
        return -1;
    }
}