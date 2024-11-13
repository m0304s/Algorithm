import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            HashMap<String, Integer> categoryMap = new HashMap<>();
            for(int i=0;i<N;i++){
                String [] tokens = br.readLine().split(" ");
                String name = tokens[0];
                String type = tokens[1];

                if(categoryMap.containsKey(type)){
                    categoryMap.put(type,categoryMap.get(type)+1);
                }else{
                    categoryMap.put(type,2);
                }
            }

            int answer = 1;
            Set<String> keySet = categoryMap.keySet();
            for (String s : keySet) {
                answer*=categoryMap.get(s);
            }
            bw.write(answer-1+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
