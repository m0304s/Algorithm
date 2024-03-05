import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<String> arrayList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            arrayList.add(br.readLine());
        }
        Set<String> set = new HashSet<>(arrayList);
        arrayList.clear();
        arrayList.addAll(set);
        
        Collections.sort(arrayList, new Comparator<String>(){
            public int compare(String o1,String o2){
                if(o1.length()==o2.length()){
                    return o1.compareTo(o2);
                }else if(o1.length()<o2.length()){
                    return -1;
                }else{
                    return 1;
                }
            }
        });

        for (String string : arrayList) {
            bw.write(string+"\n");
        }
        bw.flush();
        bw.close();
    }
}
