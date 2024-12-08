import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [][] map;
    static int N;
    static int maxValue;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        maxValue = Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        backtracking(0,map);
        bw.write(maxValue+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
//    최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록을 출력한다.
    static void backtracking(int depth,int [][] currentMap){
        if(depth == 5){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    maxValue = Math.max(maxValue,currentMap[i][j]);
                }
            }
            return;
        }

        for(int i=0;i<4;i++){
            int [][] changedMap = move(i,currentMap);
            backtracking(depth+1,changedMap);
        }
    }

    //타일의 이동
    static int [][] move(int direction, int [][] originalMap){
        int [][] newMap = new int[N][N];
        switch (direction){
            case 0: //위로 이동
                for(int i=0;i<N;i++){
                    int [] compressedCol = compressAndMerge(getColumn(originalMap,i));
                    setColumn(newMap,compressedCol,i);
                }
                break;
            case 1: //아래로 이동
                for(int i=0;i<N;i++){
                    int [] compressedCol = compressAndMerge(reverseArray(getColumn(originalMap,i)));
                    setColumn(newMap,compressedCol,i);
                }
                break;
            case 2: //왼쪽으로 이동
                for(int i=0;i<N;i++){
                    newMap[i] = compressAndMerge(originalMap[i]);
                }
                break;
            case 3: //오른쪽으로 이동
                for(int i=0;i<N;i++){
                    newMap[i] = reverseArray(compressAndMerge(reverseArray(originalMap[i])));
                }
                break;
        }
        return newMap;
    }

    static int [] compressAndMerge(int [] column){
        int [] compressed = new int[N];
        int index = 0;

        // 0이 아닌 값들만 채움
        for(int i : column){
            if(i != 0){
                compressed[index++] = i;
            }
        }

        //병합(옆에 있는 친구들 중 값이 같은 경우)
        for(int i=0;i<N-1;i++){
            if(compressed[i] == compressed[i+1] && compressed[i] != 0){
                compressed[i] *= 2;
                compressed[i+1] = 0;
            }
        }

        // 다시 압축
        int[] result = new int[N];
        index = 0;
        for (int value : compressed) {
            if (value != 0) {
                result[index++] = value;
            }
        }

        return result;
    }

    static int [] getColumn(int [][] map, int col){
        int [] column = new int[N];
        for(int i=0;i<N;i++){
            column[i] = map[i][col];
        }
        return column;
    }

    private static void setColumn(int [][] map,int[] compressedCol, int col) {
        for(int i=0;i<N;i++){
            map[i][col] = compressedCol[i];
        }
    }

    private static int[] reverseArray(int[] col) {
        int[] reversed = new int[col.length];
        for (int i = 0; i < col.length; i++) {
            reversed[i] = col[col.length - 1 - i];
        }
        return reversed;
    }
}
