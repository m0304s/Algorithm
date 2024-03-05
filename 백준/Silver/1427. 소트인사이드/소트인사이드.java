import java.util.*;
import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String num = br.readLine();
        ArrayList<Integer> array = new ArrayList<>();
        for(int i=0;i<num.length();i++){
            array.add(Integer.parseInt(Character.toString(num.charAt(i))));
        }
        Collections.sort(array,Collections.reverseOrder());
        for (Integer integer : array) {
            bw.write(Integer.toString(integer));
        }
        bw.flush();
        bw.close();
    }
}
