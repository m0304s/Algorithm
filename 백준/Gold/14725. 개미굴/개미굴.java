import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static Tree root;
	static int N,K;
	public static void main(String[] args) throws IOException{
		root = new Tree(0);
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			K = Integer.parseInt(tokens[0]);
			String [] words = new String[K];
			for(int k=0;k<K;k++) {
				words[k] = tokens[k+1];
			}
			
			insert(words);
		}
		
		//구조 출력
		print(root);
		bw.flush();
		br.close();
		bw.close();
	}
	
	static void print(Tree node) throws IOException{
		//자식이 없을 경우 리턴
		if(node.child.isEmpty()) return;
		
		for(String word : node.child.keySet()) {
			for(int i=0;i<node.depth;i++) {
				bw.write("--");
			}bw.write(word+"\n");
			print(node.child.get(word));
		}
	}
	
	static void insert(String[] words) {
		Tree node = root;
		for(int i=0;i<words.length;i++) {
			int curDepth = i+1;
			if(!node.child.containsKey(words[i])) {
				node.child.put(words[i], new Tree(curDepth));
			}
			node = node.child.get(words[i]);
		}
	}
	
	static class Tree{
		int depth;
		Map<String, Tree> child;
		
		public Tree(int depth) {
			this.child = new TreeMap<>();
			this.depth = depth;
		}
	}
}
