import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int N;
	static String[] words;
	static Trie trie;

	public static void main(String[] args) throws IOException {
		String input;
		while ((input = br.readLine()) != null && !input.equals("")) {
			N = Integer.parseInt(input);
			words = new String[N];
			trie = new Trie();

			for (int i = 0; i < N; i++) {
				words[i] = br.readLine();
				trie.insert(words[i]);
			}

			int totalCnt = 0;
			for (int i = 0; i < N; i++) {
				int cnt = trie.search(words[i]);
				totalCnt += cnt;
			}

			double answer = (double) totalCnt / N;
			bw.write(String.format("%.2f\n", answer));
		}
		bw.flush();
		bw.close();
		br.close();
	}

	static class Trie {
		Node root;

		public Trie() {
			this.root = new Node();
		}

		// 삽입 메서드
		public void insert(String word) {
			Node node = this.root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				// 문자열의 각 단어를 가져와서 자식노드에 추가
				node.child.putIfAbsent(c, new Node());
				node = node.child.get(c);
			}
			node.endOfWord = true;
		}

		// 탐색 메서드
		public int search(String word) {
			int cnt = 0;
			Node node = this.root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				
				//자판을 입력해야 하는 경우
				if(i == 0 || node.child.size() > 1 || node.endOfWord) {
					cnt++;
				}
				node = node.child.get(c);
			}
			return cnt;
		}
	}

	static class Node {
		HashMap<Character, Node> child;
		boolean endOfWord;

		public Node() {
			this.child = new HashMap<>();
			this.endOfWord = false;
		}
	}
}
