import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        Point [] list = new Point[N];
        for(int i=0;i<N;i++){
            String [] token = br.readLine().split(" ");
            list[i] = new Point(Integer.parseInt(token[0]), Integer.parseInt(token[1]));
        }
        Arrays.sort(list,new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2){
                if(o1.x==o2.x){
                    return o1.y-o2.y;
                }
                return o1.x-o2.x;
            }
        });
        for (Point point : list) {
            bw.write(Integer.toString(point.x) + " " +Integer.toString(point.y)+"\n");
        }
        bw.flush();
        bw.close();
    }
}