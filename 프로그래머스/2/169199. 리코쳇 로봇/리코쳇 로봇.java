import java.util.*;

class Solution {
    public int [] dx = {-1,1,0,0};
    public int [] dy = {0,0,-1,1};
    boolean [][] visited;
    String [][] map;
    
    class Node{
        int x;
        int y;
        int count;
        public Node(int x,int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    Queue<Node> queue = new LinkedList<>();
    public int solution(String[] board) {
        int answer = 0;
        map = new String[board.length][board[0].length()];
        visited = new boolean[board.length][board[0].length()];
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length();j++){
                map[i][j] = Character.toString(board[i].charAt(j));
                if(map[i][j].equals("R")){
                    queue.add(new Node(i,j,0));
                    visited[i][j] = true;
                }
            }
        }
        answer = bfs();
        System.out.println(answer);
        return answer;
    }
    public int bfs(){
        while(!queue.isEmpty()){
            Node now = queue.poll();
            if(map[now.x][now.y].equals("G")){
                return now.count;
            }
            for(int i=0;i<4;i++){
                int nx = now.x;
                int ny = now.y;
                //해당 방향으로 끝까지 이동(벽에 부딪힐때까지)
                while(true){
                    int nextX = nx + dx[i];
                    int nextY = ny + dy[i];
                    
                    if(nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length){
                        break;
                    }
                    if(map[nextX][nextY].equals("D")){
                        break;
                    }
                    nx = nextX;
                    ny = nextY;
                }
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.add(new Node(nx,ny,now.count+1));
                }
            }
        }
        return -1;
    }
}