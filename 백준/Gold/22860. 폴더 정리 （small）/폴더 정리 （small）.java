import java.io.*;
import java.util.*;

class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int FILE = 0, FOLDER = 1;
	private static Map<String, HashSet<String>> folders;
	private static Map<String, HashSet<String>> files;
	private static int count;
	private static HashSet<String> fileSet;
	
	private static int N,M,K,Q;
	
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		folders = new HashMap<>();
		files = new HashMap<>();
		
		for(int i=0;i<N+M;i++) {
			tokens = br.readLine().split(" ");
			String parentFolder = tokens[0];
			String name = tokens[1];
			int c = Integer.parseInt(tokens[2]);
			
			input(parentFolder,name,c);
		}
		
		Q = Integer.parseInt(br.readLine());
		for(int i=0;i<Q;i++) {
			String [] input = br.readLine().split("/");
			query(input);	
		}
		
		bw.flush();
	}
	
	private static void query(String [] path) throws IOException{
		count = 0;
		fileSet = new HashSet<>();
		
		String target = path[path.length-1];
		findFilesAndFolders(target);
		
		bw.write(fileSet.size()+" "+count+"\n");
	}
	
	private static void findFilesAndFolders(String target) {
		if(folders.containsKey(target)) {
			for(String key : folders.get(target)) {
				findFilesAndFolders(key);
			}
		}
		
		if(files.containsKey(target)) {
			for(String file : files.get(target)) {
				count++;
				fileSet.add(file);
			}
		}
	}

	private static void input(String parentFolder, String name, int c) {
		if(c == FOLDER) {
			if(!folders.containsKey(parentFolder)) folders.put(parentFolder, new HashSet<>());
			
			folders.get(parentFolder).add(name);
		}else if(c == FILE) {
			if(!files.containsKey(parentFolder)) files.put(parentFolder, new HashSet<>());
			
			files.get(parentFolder).add(name);
		}
	}
	
	

}
