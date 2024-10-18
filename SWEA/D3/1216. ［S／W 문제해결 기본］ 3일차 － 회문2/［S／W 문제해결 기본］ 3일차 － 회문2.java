import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static String[][] map = new String[100][100];

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=10;t++){
            int T = Integer.parseInt(br.readLine());
            for(int i=0;i<map.length;i++){
                String input = br.readLine();
                for(int j=0;j<map[0].length;j++){
                    map[i][j] = Character.toString(input.charAt(j));
                }
            }
            int answer = findLongestPalindrome();
            bw.write("#"+T+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int findLongestPalindrome() {
        String longestPalindrome = "";
        // 가로 회문 찾기
        for (int i = 0; i < map.length; i++) {
            for (int start = 0; start < map[0].length; start++) {
                StringBuilder sb = new StringBuilder();
                for (int end = start; end < map[0].length; end++) {
                    sb.append(map[i][end]);
                    String current = sb.toString();
                    if (current.equals(new StringBuilder(current).reverse().toString())) {
                        if (current.length() > longestPalindrome.length()) {
                            longestPalindrome = current;
                        }
                    }
                }
            }
        }

        // 세로 회문 찾기
        for (int i = 0; i < map[0].length; i++) {
            for (int start = 0; start < map.length; start++) {
                StringBuilder sb = new StringBuilder();
                for (int end = start; end < map.length; end++) {
                    sb.append(map[end][i]);
                    String current = sb.toString();
                    if (current.equals(new StringBuilder(current).reverse().toString())) {
                        if (current.length() > longestPalindrome.length()) {
                            longestPalindrome = current;
                        }
                    }
                }
            }
        }

        return longestPalindrome.length();
    }

}
