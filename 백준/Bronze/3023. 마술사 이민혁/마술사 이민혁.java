import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int R = kb.nextInt();
        int C = kb.nextInt();

        String [] t = new String[R];
        Character [][] arr = new Character[R][C];
        Character [][] answer = new Character[2*R][2*C];

        for(int i=0;i<R;i++){
            t[i]=kb.next();
        }
        int ErrX = kb.nextInt();    //에러 위치 X좌표
        int ErrY = kb.nextInt();    //에러 위치 Y좌표
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                arr[i][j]=t[i].charAt(j);
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                answer[i][j]=arr[i][j];
            }
            for(int j=0;j<C;j++){
                answer[i][j+C]=arr[i][C-j-1];
            }
        }

        for(int i=0;i<R;i++){   //0,1,2,3,4 -> 5,6,7,8,9
            for(int j=0;j<2*C;j++){
                answer[i+R][j]=answer[R-i-1][j];
            }
        }

        if(answer[ErrX-1][ErrY-1]=='.'){
            answer[ErrX-1][ErrY-1]='#';
        }else{
            answer[ErrX-1][ErrY-1]='.';
        }
        for(int i=0;i<2*R;i++){
            for(int j=0;j<2*C;j++){
                System.out.print(answer[i][j]);
            }
            System.out.println();
        }
        kb.close();
    }
}
