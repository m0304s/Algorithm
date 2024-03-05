import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Student> list = new ArrayList<>();
    public static class Student{
        String Name;
        int Korean;
        int English;
        int Math;
        public Student(String Name, int Korean, int English, int Math){
            this.Name=Name;
            this.Korean=Korean;
            this.English=English;
            this.Math=Math;
        }
    }
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            list.add(new Student(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));
        }
        Collections.sort(list,new Comparator<Student>() {
            public int compare(Student o1, Student o2){
                if(o1.Korean==o2.Korean&&o1.English==o2.English&&o1.Math==o2.Math){
                    return o1.Name.compareTo(o2.Name);
                }else if(o1.Korean==o2.Korean&&o1.English==o2.English){
                    return o2.Math-o1.Math;
                }else if(o1.Korean==o2.Korean){
                    return o1.English-o2.English;
                }else{
                    return o2.Korean-o1.Korean;
                }
            }
        });
        for (Student student : list) {
            System.out.println(student.Name);
        }
    }
}