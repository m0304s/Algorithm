import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class Turtle{
        int x;
        int y;
        Dir dir;
        public Turtle(int x,int y,Dir dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    public static enum Dir{
        동,서,남,북
    }
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            String command = br.readLine();
            Turtle turtle = new Turtle(0, 0, Dir.북);
            move(turtle,command);
        }
        bw.flush();
    }
    public static void move(Turtle turtle, String inputString) throws IOException{
        String [] commands = new String[inputString.length()];
        for(int i=0;i<inputString.length();i++){
            commands[i] = Character.toString(inputString.charAt(i));
        }
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        for (String command : commands) {
            switch(command){
                case "F":   //한 눈금 앞으로
                    if(turtle.dir == Dir.동){
                        turtle.x++;
                    }else if(turtle.dir == Dir.서){
                        turtle.x--;
                    }else if(turtle.dir == Dir.남){
                        turtle.y--;
                    }else if(turtle.dir == Dir.북){
                        turtle.y++;
                    }
                    //새롭게 설정된 거북이 좌표가 up, down, left, right의 사각형 범위를 벗어나는지 체크
                    break;
                case "B":   //한 눈금 뒤로
                    if(turtle.dir == Dir.동){
                        turtle.x--;
                    }else if(turtle.dir == Dir.서){
                        turtle.x++;
                    }else if(turtle.dir == Dir.남){
                        turtle.y++;
                    }else if(turtle.dir == Dir.북){
                        turtle.y--;
                    }
                    //새롭게 설정된 거북이 좌표가 up, down, left, right의 사각형 범위를 벗어나는지 체크
                    break;
                case "L":   //왼쪽으로 90도 회전
                    if (turtle.dir == Dir.동) {
                        turtle.dir = Dir.북;
                    } else if (turtle.dir == Dir.서) {
                        turtle.dir = Dir.남;
                    } else if (turtle.dir == Dir.남) {
                        turtle.dir = Dir.동;
                    } else if (turtle.dir == Dir.북) {
                        turtle.dir = Dir.서;
                    }
                    break;
                case "R":   //오른쪽으로 90도 회전
                    if (turtle.dir == Dir.동) {
                        turtle.dir = Dir.남;
                    } else if (turtle.dir == Dir.서) {
                        turtle.dir = Dir.북;
                    } else if (turtle.dir == Dir.남) {
                        turtle.dir = Dir.서;
                    } else if (turtle.dir == Dir.북) {
                        turtle.dir = Dir.동;
                    }
                    break;
            }
            up = Math.max(up, turtle.y);
            down = Math.min(down, turtle.y);
            left = Math.min(left, turtle.x);
            right = Math.max(right, turtle.x);
        }
        int area = (up-down)*(right - left);
        bw.write(area+"\n");
    }
}
