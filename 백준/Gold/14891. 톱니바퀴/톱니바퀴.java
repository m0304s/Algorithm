import java.io.*;
import java.util.ArrayList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int CLOCKWISE = 1;
    static final int COUNTERCLOCKWISE = -1;

    static ArrayList<char[]> cogWheels = new ArrayList<char[]>();

    public static void main(String[] args) throws IOException {
        for(int i=0;i<4;i++){
            cogWheels.add(br.readLine().toCharArray());
        }

        int moveCount = Integer.parseInt(br.readLine());

        for(int i=0;i<moveCount;i++){
            String [] tokens =br.readLine().split(" ");
            int target = Integer.parseInt(tokens[0])-1;
            int direction = Integer.parseInt(tokens[1]);

            simulate(target,direction);
        }

        int score = 0;
        for(int i=0;i<4;i++){
            if(cogWheels.get(i)[0] == '1'){
                score+=(1<<i);
            }
        }
        bw.write(score+"\n");
        bw.flush();
        br.close();
        bw.close();
    }
    static void simulate(int target, int direction){
        int [] rotations = new int[4];
        rotations[target] = direction;

        for(int i=target-1;i>=0;i--){
            if(cogWheels.get(i)[2] != cogWheels.get(i+1)[6]){
                rotations[i] = -rotations[i+1];
            }else{
                break;
            }
        }

        for(int i=target+1;i<4;i++){
            if(cogWheels.get(i)[6] != cogWheels.get(i-1)[2]){
                rotations[i] = -rotations[i-1];
            }else{
                break;
            }
        }

        for (int i=0;i<4;i++){
            rotate(i,rotations[i]);
        }
    }
    static void rotate(int target, int direction) {
        char[] cogWheel = cogWheels.get(target);
        if (direction == CLOCKWISE) {
            char last = cogWheel[7];
            for (int i = 7; i > 0; i--) {
                cogWheel[i] = cogWheel[i - 1];
            }
            cogWheel[0] = last;
        } else if (direction == COUNTERCLOCKWISE) {
            char first = cogWheel[0];
            for (int i = 0; i < 7; i++) {
                cogWheel[i] = cogWheel[i + 1];
            }
            cogWheel[7] = first;
        }
    }
}
