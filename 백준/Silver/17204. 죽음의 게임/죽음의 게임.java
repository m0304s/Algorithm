import java.io.*;
import java.util.*;

public class Main{
    static class Person{
        int index;  //자신의 번호
        int point;  //지목한 상대
        int num;    //외친 번호

        public Person(int index,int point,int num){
            this.index = index;
            this.point = point;
            this.num = num;
        }
        public String toString(){
            return "p.index = "+this.index + " p.point = "+this.point + " p.num = "+this.num;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Person> list = new ArrayList<>();

        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);    //게임에 참여하는 사람의 수
        int K = Integer.parseInt(tokens[1]);    //보성이의 번호

        for(int i=0;i<N;i++){
            list.add(new Person(i, Integer.parseInt(br.readLine()), 0));
        }
        int count =1;
        Person p = list.get(0);
        p.num = count;
        Person nextP = list.get(p.point);
        for(int i=0;i<list.size();i++){
            nextP.num = ++count;
            nextP = list.get(nextP.point);
            if(nextP.num!=0){
                break;
            }
        }
        Person target = list.get(K);
        if(target.num!=0){
            bw.write(target.num-1+"\n");
        }else{
            bw.write("-1\n");
        }
        bw.flush();
    }
}