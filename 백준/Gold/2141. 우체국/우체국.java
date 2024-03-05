import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Village> list = new ArrayList<>();

    public static class Village{
        Long pos;
        Long people;

        public Village(Long pos,Long people){
            this.pos=pos;
            this.people=people;
        }
    }
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        Long totalPeople = 0L;
        for(int i=0;i<N;i++){
            String[] s = br.readLine().split(" ");
            totalPeople+=Long.parseLong(s[1]);
            list.add(new Village(Long.parseLong(s[0]), Long.parseLong(s[1])));
        }
        Collections.sort(list,new Comparator<Village>(){    //마을 순으로 정렬
            @Override
            public int compare(Village o1, Village o2){
                return (int)(o1.pos-o2.pos);
            }
        });
        Long count = 0L;
        for (Village v : list) {
            count+=v.people;
            if(count>=(totalPeople+1)/2){
                bw.write(Long.toString(v.pos)+"\n");
                break;
            }
        }
        bw.flush();
        bw.close();
    }
}
