import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        ArrayList<String> pokList = new ArrayList<>(n+1);
        HashMap<String, Integer> pokMap = new HashMap<>();
        pokList.add("");
        for(int i=1;i<=n;i++){
            String pokName = br.readLine();
            pokList.add(pokName);
            pokMap.put(pokName, i);
        }
        for(int i=0;i<m;i++){
            String inputString = br.readLine();
            int inputNum = 0;
            boolean isNum = false;
            try{
                inputNum = Integer.parseInt(inputString);
                isNum = true;
            }catch(NumberFormatException ex){

            }
            if(isNum){
                bw.write(pokList.get(inputNum)+"\n");
            }else{
                bw.write(pokMap.get(inputString)+"\n");
            }
        }
        bw.flush();
    }
}
