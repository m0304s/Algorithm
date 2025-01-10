import java.io.*;
import java.util.*;

class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static HashMap<Integer, Integer> answerMap;

    public static void main(String[] args) throws IOException {
    	answerMap = new HashMap<>();
    	List<Integer> numbers = new ArrayList<>();
    	List<Integer> sortedNumbers = new ArrayList<>();
    	int N = Integer.parseInt(br.readLine());
    	String [] tokens = br.readLine().split(" ");
    	for(int i=0;i<N;i++) {
    		int a = Integer.parseInt(tokens[i]);
    		numbers.add(a);
    		sortedNumbers.add(a);
    	}
    	
    	Collections.sort(sortedNumbers);
    	
    	int rank = 0;
    	for(int num : sortedNumbers) {
    		if(!answerMap.containsKey(num)) {
    			answerMap.put(num, rank);
    			rank++;
    		}
    	}
    	
    	for(int key : numbers) {
    		bw.write(answerMap.get(key)+" ");
    	}
    	bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
