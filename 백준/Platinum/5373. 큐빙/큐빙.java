import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int UP = 0, DOWN = 1, FRONT = 2, BACK = 3,LEFT = 4, RIGHT = 5;

    //윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색, 왼쪽 면은 초록색, 오른쪽 면은 파란색
    private static char [] colors = {'w','y','r','o','g','b'};
    private static char [][][] cube;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            init();
            int N = Integer.parseInt(br.readLine());    //큐브를 돌릴 횟수
            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                turn(tokens[i]);
            }
            printTop();
        }
    }

    private static void printTop() throws IOException{
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                bw.write(cube[UP][j][2-i]+"");
            }bw.write("\n");
        }
        bw.flush();
    }

    private static void turn(String input){
        char direction = input.charAt(0);
        boolean flag = input.charAt(1) == '+';

        switch (direction){
            case 'U':
                alter(UP,LEFT,FRONT,RIGHT,BACK,flag);
                break;
            case 'D':
                alter(DOWN,BACK,RIGHT,FRONT,LEFT,flag);
                break;
            case 'F':
                alter(FRONT,UP,LEFT,DOWN,RIGHT,flag);
                break;
            case 'B':
                alter(BACK,RIGHT,DOWN,LEFT,UP,flag);
                break;
            case 'L':
                alter(LEFT,FRONT,UP,BACK,DOWN,flag);
                break;
            case 'R':
                alter(RIGHT,DOWN,BACK,UP,FRONT,flag);
                break;
            default:
                break;
        }
    }

    private static void alter(int front, int up, int left, int down, int right, boolean flag){
        char [][] upsideTemp = new char[3][3];
        char [] sideTemp = new char[3];
        if(flag){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    upsideTemp[i][j] = cube[front][2-j][i];
                }
            }

            for(int i=0;i<3;i++){
                sideTemp[i] = cube[up][i][0];
            }
            for(int i=0;i<3;i++){
                cube[up][i][0] = cube[left][0][2-i];
            }
            for(int i=0;i<3;i++){
                cube[left][0][2-i] = cube[down][2][i];
            }
            for(int i=0;i<3;i++){
                cube[down][2][i] = cube[right][2-i][2];
            }
            for(int i=0;i<3;i++){
                cube[right][2-i][2] = sideTemp[i];
            }
        }else{
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    upsideTemp[i][j] = cube[front][j][2-i];
                }
            }
            for(int i=0;i<3;i++){
                sideTemp[i] = cube[up][i][0];
            }
            for(int i=0;i<3;i++){
                cube[up][i][0] = cube[right][2-i][2];
            }
            for(int i=0;i<3;i++){
                cube[right][2-i][2] = cube[down][2][i];
            }
            for(int i=0;i<3;i++){
                cube[down][2][i] = cube[left][0][2-i];
            }
            for(int i=0;i<3;i++){
                cube[left][0][2-i] = sideTemp[i];
            }
        }
        cube[front] = upsideTemp;
    }

    private static void init(){
        cube = new char[6][3][3];
        for(int i=0;i<6;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    cube[i][j][k] = colors[i];
                }
            }
        }
    }
}
