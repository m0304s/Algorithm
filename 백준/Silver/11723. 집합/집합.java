import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        Map<Integer, Integer> all = new HashMap<>();
        for(int i=1;i<=20;i++){
            all.put(i,0);
        }
        int M = Integer.parseInt(br.readLine());
        int commandNum = 0;
        for(int i=0;i<M;i++){
            String [] tokens = br.readLine().split(" ");
            String command = tokens[0];
            if(tokens.length == 2){
                commandNum = Integer.parseInt(tokens[1]);
            }
            if(command.equals("add")){
                if(all.get(commandNum)!=1){ //기존에 존재하지 않는 경우
                    all.put(commandNum, 1);
                }else{
                    continue;
                }
            }else if(command.equals("remove")){
                if(all.get(commandNum)==1){
                    all.put(commandNum,0);
                }else{
                    continue;
                }
            }else if(command.equals("check")){
                bw.write(all.get(commandNum)+"\n");
            }else if(command.equals("toggle")){
                if(all.get(commandNum)!=1){ //기존에 존재하지 않는 경우
                    all.put(commandNum, 1);
                }else{
                    all.put(commandNum,0);
                }
            }else if(command.equals("all")){
                for(int j=1;j<=20;j++){
                    all.put(j,1);
                }
            }else{
                for(int j=1;j<=20;j++){
                    all.put(j,0);
                }
            }
        }
        bw.flush();
    }
}
