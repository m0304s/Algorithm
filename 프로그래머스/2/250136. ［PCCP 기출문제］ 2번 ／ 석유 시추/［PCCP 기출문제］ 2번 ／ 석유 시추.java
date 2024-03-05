import java.util.*;

class Solution {
    public static ArrayList<int []> countList = new ArrayList<>();
    public static int n;
    public static int m;
    public static int[] dx = {-1,1,0,0};   //상하좌우
    public static int[] dy = {0,0,-1,1};
    public static Map<Integer, Integer> oilVol = new HashMap<>();
    public int solution(int[][] land) {
        boolean [][] visited = new boolean[land.length][land[0].length];
        n = land.length;
        m = land[0].length;
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[0].length;j++){
                if(land[i][j]==1 && !visited[i][j]){
                    bfs(land,new int[]{i,j},visited);
                }
            }
        }
        int answer = 0;
        for(int s : oilVol.keySet()){
            if(oilVol.get(s)>answer){
                answer = oilVol.get(s);
            }
        }
        return answer;
    }
    public void bfs(int[][] land, int [] point, boolean[][] visited){
        HashSet<Integer> hashSet = new HashSet<>();
        int size = 1;   //초기 사이즈 1
        visited[point[0]][point[1]] = true;
        Queue <int[]> q = new LinkedList<>();
        q.add(point);
        hashSet.add(point[1]);
        while(!q.isEmpty()){
            int [] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            
            for(int i=0;i<4;i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
            
                if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m){
                    continue;
                }
                if(visited[nextX][nextY] || land[nextX][nextY] == 0){
                    continue;
                }
                q.add(new int[] {nextX,nextY});
                hashSet.add(nextY);
                size++;
                visited[nextX][nextY] = true;
            }
        }
        // System.out.println(size);
        for(int y : hashSet){
            oilVol.put(y,oilVol.getOrDefault(y,0) + size);
        }
        
    }
}