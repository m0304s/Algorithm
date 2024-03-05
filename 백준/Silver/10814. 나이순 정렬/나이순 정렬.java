import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Person {
        int age;
        String name;
        public Person(int age, String name) {
            this.age=age;
            this.name=name;
        }
    }

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        Person[] list = new Person[N];
        for(int i=0;i<N;i++){
            String input = br.readLine();
            String [] token = input.split(" ");
            list[i] = new Person(Integer.parseInt(token[0]),token[1]);
        }

        Arrays.sort(list,new Comparator<Person>(){
            @Override
            public int compare(Person o1, Person o2){
                return o1.age-o2.age;
            }
        });
        
        for (Person person : list) {
            bw.write(Integer.toString(person.age)+" "+person.name+"\n");
        }

        bw.flush();
        bw.close();
    }
}
