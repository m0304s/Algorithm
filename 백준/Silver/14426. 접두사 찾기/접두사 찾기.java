import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static Node rootNode = new Node();
    private static class Node{
        Map<Character, Node> childNode = new HashMap<>();
        boolean isEndOfWord;
    }

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        for(int i=0;i<N;i++){
            insert(br.readLine());
        }

        int count = 0;
        for(int i=0;i<M;i++){
            boolean result = search(br.readLine());
            if(result) count++;
        }
        bw.write(count+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void insert(String word){
        Node node = rootNode;
        for(int i=0;i<word.length();i++){
            node = node.childNode.computeIfAbsent(word.charAt(i), key -> new Node());
        }
        node.isEndOfWord = true;
    }

    private static boolean search(String word){
        Node node = rootNode;
        for(int i=0;i<word.length();i++){
            node = node.childNode.getOrDefault(word.charAt(i),null);
            if(node == null) return false;
        }
        return true;
    }
}
