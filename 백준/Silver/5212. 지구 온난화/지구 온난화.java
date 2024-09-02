import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int R = Integer.parseInt(tokens[0]);
        int C = Integer.parseInt(tokens[1]);

        String [][] map = new String[R][C];
        for(int i=0;i<R;i++){
            String input = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = Character.toString(input.charAt(j));
            }
        }

        int count = 0;  // 바다의 개수

        int up = R;     //지도의 가장 위
        int down = 0;   //지도의 가장 아래
        int left = C;   //지도의 가장 왼쪽
        int right = 0;  //지도의 가장 오른쪽
        
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j].equals("X")){  //섬을 만났을 경우 4방탐색
                    count = 0;
                    for(int k=0;k<4;k++){
                        int newX = i+dx[k];
                        int newY = j+dy[k];
                        if(newX>=0 && newX<R && newY>=0 && newY<C){
                            if(map[newX][newY].equals(".")){    //4방향 중 바다의 개수를 count
                                count++;
                            }
                        }else{
                            count++;    //지도 바깥부분도 바다로 취급
                        }
                    }
                    if(count>=3){
                        map[i][j] = "S";
                    }
                }
                if(map[i][j].equals("X")){  //새롭게 작성할 지도의 크기 갱신
                    up = Math.min(up,i);
                    down = Math.max(down, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }

        //새롭게 지도 그리기
        for(int i=up;i<=down;i++){
            for(int j=left;j<=right;j++){
                if(map[i][j].equals("X")){
                    bw.write("X");
                }else{
                    bw.write(".");
                }
            }bw.write("\n");
        }
        bw.flush();
    }
}
