import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String[] tokens = br.readLine().split("\\-");
        int sum=0;
        for(int i=0;i<tokens.length;i++){
            String [] split = tokens[i].split("\\+");
            if(i==0){
                for (String string : split) {
                    sum+=Integer.parseInt(string);
                }
            }else{
                for (String string : split) {
                    sum-=Integer.parseInt(string);
                }
            }
        }
        bw.write(Integer.toString(sum)+"\n");
        bw.flush();
        bw.close();
    }
}
