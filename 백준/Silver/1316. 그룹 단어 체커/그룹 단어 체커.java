import java.io.*;
import java.util.HashMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static HashMap<Character,Integer> map;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int groupWordCnt = 0;
        for(int i=0;i<N;i++){
            map = new HashMap<>();
            String targetWord = br.readLine();
            boolean isGroupWord = true;
            for(int j=0;j<targetWord.length();j++){
                Character character = targetWord.charAt(j);
                int characterIndex = j;
                if(!map.containsKey(character)){
                    map.put(character,characterIndex);
                }else{
                    if(characterIndex != map.get(character)+1){
                        isGroupWord = false;
                        break;
                    }else{
                        map.put(character,map.get(character)+1);
                    }
                }
            }
            if(isGroupWord) groupWordCnt++;
        }
        bw.write(groupWordCnt+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
