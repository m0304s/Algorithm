import java.io.*;
import java.util.ArrayList;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            ArrayList<Integer> list = new ArrayList<>();
            String [] s = br.readLine().split(" ");
            for (String string : s) {
                list.add(Integer.parseInt(string));
            }
            bw.write(Integer.toString(solution(list))+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static int solution(ArrayList<Integer> list){
        int x1 = list.get(0);
        int y1 = list.get(1);
        int r1 = list.get(2);
        int x2 = list.get(3);
        int y2 = list.get(4);
        int r2 = list.get(5);

        //distance = 중심사이 거리
        double distance = (int)(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        // case 1 : 중점이 같으면서 반지름도 같을 경우
        if(x1==x2 && y1==y2 && r1==r2){
            return -1;
        }
        // case 2-1 : 두 원의 반지름 합보다 중점간 거리가 더 길 때 
        else if(distance>Math.pow(r1+r2, 2)){
            return 0;
        }
        // case 2-2 : 원 안에 원이 있으나 내접하지 않을 때 
        else if(distance<Math.pow(r2-r1, 2)){
            return 0;
        }
        // case 3-1 : 내접할 때 
        else if(distance==Math.pow(r2-r1, 2)){
            return 1;
        }
        // case 3-2 : 외접할 때 
        else if(distance==Math.pow(r1+r2, 2)){
            return 1;
        }else{
            return 2;
        }
    }
}
