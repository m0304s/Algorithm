import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static HashMap<String, ArrayList<String>> hash = new HashMap<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            hash.clear();
            int numOfCloths = Integer.parseInt(br.readLine());
            for(int j=0;j<numOfCloths;j++){
                String [] tokens = br.readLine().split(" ");
                String cloth = tokens[0];
                String category = tokens[1];

                if(!hash.containsKey(category)){
                    ArrayList<String> list = new ArrayList<>();
                    list.add(cloth); 
                    hash.put(category, list);
                }else{
                    ArrayList<String> list = hash.get(category);
                    list.add(cloth);
                }
            }
            // Category 별로 조합을 구함
            Set<String> keys = hash.keySet();
            ArrayList<Integer> cntlist = new ArrayList<>();
            for(String s : keys){
                int categoryCnt = hash.get(s).size();   //각 카테고리에 몇 가지의 의류가 있는지
                categoryCnt++;  //안 입는 경우 고려
                cntlist.add(categoryCnt);
            }
            int answer = 1;
            for(Integer j : cntlist){
                answer = answer*j;
            }
            bw.write(Integer.toString(answer-1)+"\n");
        }
        bw.flush();
    }
}