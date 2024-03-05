class Solution {
   public int curPos[]=new int[2];
    public int[] solution(String[] park, String[] routes) {
        //출발점 찾기
        findStartingPoint(park);
        for(int i=0;i<routes.length;i++){
            char op = routes[i].charAt(0);  //이동 방향
            //이동 크기
            int n = Character.getNumericValue(routes[i].charAt(2));
            switch(op){
                case 'E': {
                    moveEast(park,n);
                    continue;
                }
                case 'W': {
                    moveWest(park,n);
                    continue;
                }
                case 'S': {
                    moveSouth(park,n);
                    continue;
                }
                case 'N': {
                    moveNorth(park,n);
                    continue;
                }
            }
        }
        return curPos;
    }
    private void moveNorth(String[] park, int n) {
        //북쪽 == 세로좌표 -n
        if(curPos[0]-n<0) return;
        int j=1;
        while(j<=n){
            if(park[curPos[0]-j].charAt(curPos[1])=='X'){
                return;
            }
            j++;
        }
        curPos[0]-=n;
    }
    private void moveSouth(String[] park, int n) {
        //남쪽 == 세로좌표 +n
        if(curPos[0]+n>=park[0].length()) return;
        int j=1;
        while(j<=n){
            if(park[curPos[0]+j].charAt(curPos[1])=='X'){
                return;
            }
            j++;
        }
        curPos[0]+=n;
    }
    private void moveWest(String[] park, int n) {
        //서쪽 == 가로좌표 -n
        if(curPos[1]-n<0) return;
        for(int j=1;j<=n;j++){
            if(park[curPos[0]].charAt(curPos[1]-j)=='X'){
                return;
            }
        }
        curPos[1]-=n;
    }
    private void moveEast(String[] park, int n) {
        //동쪽 == 가로좌표 + n
        if(curPos[1]+n>=park[0].length()) return;   //이동할 수 없을 경우
        for(int j=1;j<=n;j++){
            if(park[curPos[0]].charAt(curPos[1]+j)=='X'){
                return;
            }   //장애물에 가로막힌 경우
        }
        curPos[1]+=n;
    }
    private void findStartingPoint(String[] park) {
        for(int i=0;i<park.length;i++){
            for(int j=0;j<park[i].length();j++){
                if(park[i].charAt(j)=='S'){
                    curPos[0]=i;    //세로 좌표
                    curPos[1]=j;    //가로 자표
                    return;
                }
            }
        }
    }
}