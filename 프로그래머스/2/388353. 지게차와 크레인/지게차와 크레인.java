import java.util.*;

class Solution {
    static Character [][] storages;
    static String [] requests;
    
    public int solution(String[] storage, String[] requests) {
        this.requests = requests;
        
        processStorage(storage);
        
        for(String request : requests){
            if(request.length() == 1){
                //지게차를 이용한 출고 요청
                Character type = request.charAt(0);
                removeAllContainer(type);
            }else{
                //크레인을 이용한 출고 요청
                Character type = request.charAt(0); //꺼낼 컨테이너 명
                for(int i=0;i<storages.length;i++){
                    for(int j=0;j<storages[i].length;j++){
                        if(type == storages[i][j]) storages[i][j] = null;
                    }
                }
            }
        }
        
        int n = storages.length;
        int m = storages[0].length;
        int answer = 0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(storages[i][j] != null) answer++;
            }
        }
        return answer;
    }
    
    static void removeAllContainer(Character type){
        int n = storages.length;
        int m = storages[0].length;
        
        boolean [][] visited = new boolean[n][m];
        Queue<Node> queue = new ArrayDeque<>();
        List<Node> toRemove = new ArrayList<>();
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i == 0 || i == n - 1 || j == 0 || j == m - 1){
                    if(storages[i][j] == null){
                        queue.add(new Node(i,j));
                        visited[i][j] = true;
                    }else if(storages[i][j] == type){
                        toRemove.add(new Node(i,j));
                        visited[i][j] = true;
                    }
                }
            }
        }
        
        int [] dx = {0,0,-1,1};
        int [] dy = {-1,1,0,0};
        
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            
            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];
                
                if(!inRange(nx,ny, n, m) || visited[nx][ny]) continue;
                
                //빈 공간이면 계속 타고 들어갈 수 있으니 BFS 큐에 추가
                if(storages[nx][ny] == null){
                    queue.add(new Node(nx,ny));
                    visited[nx][ny] = true;
                }else if(storages[nx][ny] == type){
                    toRemove.add(new Node(nx,ny));
                    visited[nx][ny] = true;
                }
            }
        }
        
        for(Node node : toRemove){
            storages[node.x][node.y] = null;
        }
    }
    
    static void processStorage(String [] storage){
        int n = storage.length;
        int m = storage[0].length();
        
        storages = new Character[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                storages[i][j] = storage[i].charAt(j);
            }
        }
    }
    
    static boolean inRange(int x, int y, int n, int m){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
    
    static void printStorage(){
        for(int i=0;i<storages.length;i++){
            for(int j=0;j<storages[0].length;j++){
                System.out.print(storages[i][j] + " ");
            }System.out.println();
        }
    }
    
    static class Node{
        int x,y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public String toString(){
            return "X : " + x + " Y : " + y;
        }
    }
}