import java.io.*;
import java.util.*;

public class Main {

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    //우 좌 상 하
    private static final int [] dx = {0,0,-1,1};
    private static final int [] dy = {1,-1,0,0};
    private static final int WHITE = 0, RED = 1, BLUE = 2;

    private static class Chess{
        int color;
        ArrayList<Integer> nodeList;

        public Chess(int color) {
            this.color = color;
            this.nodeList = new ArrayList<>();
        }
    }

    private static class Node{
        int number;
        int x,y;
        int direction;

        public Node(int number,int x,int y, int direction) {
            this.number = number;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
        
        public void updateNode(int x,int y) {
        	this.x = x;
        	this.y = y;
        }
        
        public void updateDirection(int reverseDirection) {
        	this.direction = reverseDirection;
        }
    }

    private static int N,K;
    private static Chess[][] chessMap;
    private static ArrayList<Node> points;

    public static void main(String [] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        K = Integer.parseInt(tokens[1]);

        chessMap = new Chess[N][N];
        points = new ArrayList<>();

        for(int i=0;i<N;i++) {
            tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++) {
                int color = Integer.parseInt(tokens[j]);
                chessMap[i][j] = new Chess(color);
            }
        }

        for(int i=0;i<K;i++) {
            tokens = br.readLine().split(" ");
            int x = Integer.parseInt(tokens[0])-1;
            int y = Integer.parseInt(tokens[1])-1;
            int direction = Integer.parseInt(tokens[2])-1;

            points.add(new Node(i,x,y,direction));
            chessMap[x][y].nodeList.add(i);
        }

        simulation();
    }

    private static void simulation() throws IOException {
        int turn = 0;
        while(true) {
            if(turn > 1000) {
                bw.write("-1\n");
                bw.flush();
                break;
            }
            turn++;
            
            for(int i=0;i<K;i++) {
                Node cur = points.get(i);
                
                // 현재 말이 가장 아래에 있는 말인지 확인
                if(chessMap[cur.x][cur.y].nodeList.get(0) != cur.number) {
                    continue;
                }
                
                int startIndex = chessMap[cur.x][cur.y].nodeList.indexOf(cur.number);
                int endIndex = chessMap[cur.x][cur.y].nodeList.size();
                List<Integer> moveList = new ArrayList<>(chessMap[cur.x][cur.y].nodeList.subList(startIndex, endIndex));
                
                int nx = cur.x + dx[cur.direction];
                int ny = cur.y + dy[cur.direction];
                
                if(!inRange(nx,ny) || chessMap[nx][ny].color == BLUE) {
                    // 방향 전환
                    cur.updateDirection(getReverseDirection(cur.direction));
                    nx = cur.x + dx[cur.direction];
                    ny = cur.y + dy[cur.direction];
                    
                    // 반대 방향도 범위를 벗어나거나 파란색이면 이동하지 않음
                    if(!inRange(nx,ny) || chessMap[nx][ny].color == BLUE) {
                        continue;
                    }
                    
                    if(chessMap[nx][ny].color == RED) {
                        chessMap[cur.x][cur.y].nodeList.removeAll(moveList);
                        Collections.reverse(moveList);
                        chessMap[nx][ny].nodeList.addAll(moveList);
                        updateMoveList(nx,ny,moveList);
                    }else if(chessMap[nx][ny].color == WHITE) {
                    	chessMap[cur.x][cur.y].nodeList.removeAll(moveList);
                    	chessMap[nx][ny].nodeList.addAll(moveList);
                    	updateMoveList(nx,ny,moveList);
                    }
                } else if(chessMap[nx][ny].color == RED) {
                    chessMap[cur.x][cur.y].nodeList.removeAll(moveList);
                    Collections.reverse(moveList);
                    chessMap[nx][ny].nodeList.addAll(moveList);
                    updateMoveList(nx,ny,moveList);
                } else if(chessMap[nx][ny].color == WHITE) {
                    chessMap[cur.x][cur.y].nodeList.removeAll(moveList);
                    chessMap[nx][ny].nodeList.addAll(moveList);
                    updateMoveList(nx,ny,moveList);
                }
            }
            if (!checkContinueGame()) {
                bw.write(turn + "\n");
                bw.flush();
                break;
            }
    	}
    	bw.close();
    	br.close();
    }
    
    private static boolean inRange(int x,int y) {
    	return x >= 0 && x < N && y >= 0 && y < N;
    }
    
    private static void updateMoveList(int targetX,int targetY,List<Integer> moveList) {
    	for(int idx : moveList) {
    		points.get(idx).updateNode(targetX, targetY);
    	}
    }
    
    private static int getReverseDirection(int direction) {
    	switch(direction) {
	    	case 0: return 1;
	    	case 1: return 0;
	    	case 2: return 3;
	    	case 3: return 2;
	    	default: return -1;
    	}
    }
    
    private static boolean checkContinueGame() {
    	for(int i=0;i<K;i++) {
    		Node cur = points.get(i);
    		if(chessMap[cur.x][cur.y].nodeList.size() >= 4) return false;
    	}
    	return true;
    }
}

