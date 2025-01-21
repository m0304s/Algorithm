import java.io.*;
import java.util.*;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static class Problem implements Comparable<Problem>{
		int num;
		int level;
		int type;
		
		public int compareTo(Problem o) {
			if(this.level == o.level) {
				return this.num - o.num;
			}else {
				return this.level - o.level;
			}
		}
		
		public Problem(int num, int level, int type) {
			this.num = num;
			this.level = level;
			this.type = type;
		}
	}
	
	private static TreeSet<Problem> treeSet;
	private static Map<Integer, Integer> levelMap;
	private static Map<Integer, Integer> algoMap;
	private static Map<Integer, TreeSet<Problem>> algoTreeMap;
	
	public static void main(String [] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		treeSet = new TreeSet<>();
		levelMap = new HashMap<>();
		algoTreeMap = new HashMap<>();
		algoMap = new HashMap<>();
		for(int i=0;i<N;i++) {
			String [] tokens = br.readLine().split(" ");
			int num = Integer.parseInt(tokens[0]);
			int level = Integer.parseInt(tokens[1]);
			int type = Integer.parseInt(tokens[2]);
			
			treeSet.add(new Problem(num,level,type));
			levelMap.put(num, level);
			algoMap.put(num, type);
			if(!algoTreeMap.containsKey(type)) {
				algoTreeMap.put(type, new TreeSet<Problem>());
				algoTreeMap.get(type).add(new Problem(num,level,type));
			}else {
				algoTreeMap.get(type).add(new Problem(num,level,type));
			}
		}
		
		int commandLineNum = Integer.parseInt(br.readLine());
		for(int i=0;i<commandLineNum;i++) {
			String [] tokens = br.readLine().split(" ");
			if(tokens[0].equals("recommend")) {
				int type = Integer.parseInt(tokens[1]);
				int x = Integer.parseInt(tokens[2]);
				if(x == -1) {
					bw.write(algoTreeMap.get(type).first().num+"\n");
				}else if(x == 1) {
					bw.write(algoTreeMap.get(type).last().num+"\n");
				}
			}else if(tokens[0].equals("recommend2")) {
				int x = Integer.parseInt(tokens[1]);
				if(x == -1) {
					bw.write(treeSet.first().num+"\n");
				}else if(x == 1) {
					bw.write(treeSet.last().num+"\n");
				}
			}else if(tokens[0].equals("recommend3")) {
				int x = Integer.parseInt(tokens[1]);
				int L = Integer.parseInt(tokens[2]);
				
				if(x == -1) {
					Problem p = treeSet.floor(new Problem(0,L,0));
					if(p == null) {
						bw.write("-1\n");
					}else {
						bw.write(p.num+"\n");	
					}
				}else if(x == 1) {
					Problem p = treeSet.ceiling(new Problem(0,L,0));
					if(p == null) {
						bw.write("-1\n");
					}else {
						bw.write(p.num+"\n");	
					}
				}
			}else if(tokens[0].equals("add")) {
				int num = Integer.parseInt(tokens[1]);
				int level = Integer.parseInt(tokens[2]);
				int type = Integer.parseInt(tokens[3]);
				
				treeSet.add(new Problem(num,level,type));
				levelMap.put(num, level);
				algoMap.put(num, type);
				if(!algoTreeMap.containsKey(type)) {
					TreeSet<Problem> temp = new TreeSet<>();
					temp.add(new Problem(num,level,type));
					algoTreeMap.put(type, temp);
				}else {
					TreeSet<Problem> temp = algoTreeMap.get(type);
					temp.add(new Problem(num,level,type));
					algoTreeMap.replace(type, temp);
				}
			}else if(tokens[0].equals("solved")) {
				int num = Integer.parseInt(tokens[1]);
				int level = levelMap.get(num);
				int type = algoMap.get(num);
				treeSet.remove(new Problem(num,level,type));
				algoTreeMap.get(type).remove(new Problem(num,level,type));
				levelMap.remove(num);
				algoMap.remove(num);
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}