import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution {
	/**
	 * 규영이가 내는 카드 순서는 고정되어 있음
	 * 규영이가 가진 카드 -> 인영이는 가질 수 없는 카드
	 * 
	 * 인영이가 가진 카드 Set 구함
	 * 인영이가 낼 수 있는 카드 조합 계산 => Permutation 연산으로 완전 탐색
	 * 만약 인영이가 내는 순서를 다 결정한 경우 => 인영이와 규영이가 1장씩 카드를 내서 규영이가 이기는지, 인영이가 이기는지 체크
	 * 
	 * 만약 결정하지 않은 경우 => 현재 단계에서 낼 카드를 선택한 뒤 재귀호출을 통해 다음 제출할 카드 선택
	 * @param args
	 */
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int guyongWin, inyoungWin;
	private static int [] guyoungCards, inyoungCards;
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			solution();
			bw.write("#"+t+" "+guyongWin +" "+inyoungWin+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void solution() throws IOException{
		String [] tokens = br.readLine().split(" ");
		HashSet<Integer> set = new HashSet<>();
		guyongWin = inyoungWin = 0;
		guyoungCards = new int[9];
		inyoungCards = new int[9];
		
		for(int i=0;i<tokens.length;i++) {
			guyoungCards[i] = Integer.parseInt(tokens[i]);
			set.add(guyoungCards[i]);
		}
		
		int size = 0;
		for(int i=1;i<=18;i++) {
			int num = i;
			
			if(set.contains(num)) continue;
			
			inyoungCards[size++] = i;
		}
		
		permutation(0,new int[9],new boolean[9]);
	}
	
	private static void permutation(int depth, int[] dist, boolean [] visited) {
		if(depth == 9) {
			//카드를 서로 내면서 점수 계산
			
			int inyoung = 0;
			int guyoung = 0;
			
			for(int i=0;i<9;i++) {
				if(dist[i] > guyoungCards[i]) {
					inyoung+=(dist[i] + guyoungCards[i]);
				}else if(dist[i] < guyoungCards[i]) {
					guyoung+=(dist[i] + guyoungCards[i]);
				}
			}
			
			if(inyoung > guyoung) {
				inyoungWin++;
			}else if(inyoung < guyoung) {
				guyongWin++;
			}
		}
		
		for(int i=0;i<9;i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			dist[depth] = inyoungCards[i];
			permutation(depth+1, dist, visited);
			visited[i] = false;
		}
	}
}