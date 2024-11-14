import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String inputValue;
    static int N,commands;
    static List<String> passwords;

    public static void main(String[] args) throws IOException {
        int T = 10;
        for(int t=1;t<=T;t++){
            passwords = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            String [] tokens = br.readLine().split(" ");
            for(int i=0;i<N;i++){
                passwords.add(tokens[i]);
            }
            commands = Integer.parseInt(br.readLine());

            tokens = br.readLine().split(" ");
            for(int i=0;i<tokens.length;i++){
                String command = tokens[i];
                if(command.equals("I")){
                    int x = Integer.parseInt(tokens[i+1]);
                    int y = Integer.parseInt(tokens[i+2]);

                    List<String> passwordsToAdd = new ArrayList<>();
                    for(int j=0;j<y;j++){
                        passwordsToAdd.add(tokens[i+3+j]);
                    }
                    passwords.addAll(x,passwordsToAdd);

                    i+=(2+y);

                }else if(command.equals("D")){
                    int x = Integer.parseInt(tokens[i+1]);
                    int y = Integer.parseInt(tokens[i+2]);
                    i+=(2);
                    for(int j=0;j<y;j++){
                        passwords.remove(x+j);
                    }
                }else if(command.equals("A")){
                    int y = Integer.parseInt(tokens[i+1]);
                    List<String> passwordsToAdd = new ArrayList<>();

                    for(int j=0;j<y;j++){
                        passwordsToAdd.add(tokens[i+2+j]);
                    }
                    passwords.addAll(passwordsToAdd);
                    i+=(1+y);
                }
            }
            bw.write("#"+t+" ");
            for(int i=0;i<10;i++){
                bw.write(passwords.get(i)+" ");
            }bw.write("\n");
            passwords.clear();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
