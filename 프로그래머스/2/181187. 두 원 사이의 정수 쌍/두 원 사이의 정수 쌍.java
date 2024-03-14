class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long r2Count = 0;
        for(int i=1;i<=r2;i++){
            int y2 = (int) Math.floor(Math.sqrt((long)r2*r2-(long)i*i))+1;
            r2Count+=y2;
        }
        r2Count=r2Count*4+1;
        
        long r1Count = 0;
        long edge = 0;
        for (int i = 1; i <= r1; i++) {
             double yPowSqrt = Math.sqrt(((long) r1 * r1 - (long) i * i));
             int y1 = (int) Math.floor(yPowSqrt) + 1;
             r1Count += y1;
             if (yPowSqrt % 1 == 0.0)
                 edge += 1;
        }
        r1Count=r1Count*4+1;
        return r2Count-r1Count+edge*4;
    }
}