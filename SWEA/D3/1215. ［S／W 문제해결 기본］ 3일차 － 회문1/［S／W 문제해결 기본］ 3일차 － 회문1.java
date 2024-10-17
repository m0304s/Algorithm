import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String [][] map = new String[8][8];

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            int wordLength = Integer.parseInt(br.readLine());   //찾아야 하는 단어의 개수
            for(int i=0;i<8;i++){
                String inputString = br.readLine();
                for(int j=0;j<8;j++){
                    map[i][j] = Character.toString(inputString.charAt(j));
                }
            }
            int answer = findPalindrome(wordLength);
            bw.write("#"+t+" "+answer+'\n');
        }
        bw.flush();
        br.close();
        bw.close();
    }
    static int findPalindrome(int wordLength){
        int count = 0;

        //가로 회문
        for(int i=0;i<8;i++){
            for(int j=0;j<=8-wordLength;j++){
                StringBuilder sb = new StringBuilder();
                for(int k=0;k<wordLength;k++){
                    sb.append(map[i][j+k]);
                }
                if(sb.toString().equals(sb.reverse().toString())) count++;  //회문일 경우 count+1
            }
        }

        //세로 회문
        for(int i=0;i<8;i++){
            for(int j=0;j<=8-wordLength;j++){
                StringBuilder sb = new StringBuilder();
                for(int k=0;k<wordLength;k++){
                    sb.append(map[j+k][i]);
                }
                if(sb.toString().equals(sb.reverse().toString())) count++;  //회문일 경우 count+1
            }
        }
        return count;
    }
}
