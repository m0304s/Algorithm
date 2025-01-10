import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N, M;

    public static void main(String[] args) throws IOException {
    	int [] preArr = new int[1000001];
    	int [] postArr = new int[1000001];
    	
    	String [] tokens = br.readLine().split(" ");
    	N = Integer.parseInt(tokens[0]);
    	M = Integer.parseInt(tokens[1]);
    	
    	tokens = br.readLine().split(" ");
    	
    	int firstStation = Integer.parseInt(tokens[0]);
    	int lastStation = Integer.parseInt(tokens[N-1]);
    	int prevStation = firstStation;
    	for(int i=1;i<N-1;i++) {
    		int station = Integer.parseInt(tokens[i]);
    		preArr[station] = prevStation;
    		postArr[prevStation] = station;
    		prevStation = station;
    	}
    	//마지막 역 넣기
    	postArr[prevStation] = lastStation;
    	preArr[lastStation] = prevStation;
    	postArr[lastStation] = firstStation;
    	preArr[firstStation] = lastStation;
    	
    	for(int m=0;m<M;m++) {
        	tokens = br.readLine().split(" ");
        	String command = tokens[0];
        	if(command.equals("BN")) {	//i 다음 역의 고유 번호를 출력, i -> j -> i 다음역
        		int i = Integer.parseInt(tokens[1]);
        		int j = Integer.parseInt(tokens[2]);
        		
        		int iPost = postArr[i];
        		postArr[i] = j;
        		preArr[iPost] = j;
        		preArr[j] = i;
        		postArr[j] = iPost;
        		
        		bw.write(iPost+"\n");
        	}else if(command.equals("BP")) {	//i 이전 역의 고유 번호 출력, i 이전역 -> j -> i
        		int i = Integer.parseInt(tokens[1]);
        		int j = Integer.parseInt(tokens[2]);
        		
        		int iPrev = preArr[i];
        		postArr[iPrev] = j;
        		preArr[i] = j;
        		postArr[j] = i;
        		preArr[j] = iPrev;
        		
        		bw.write(iPrev+"\n");
        	}else if(command.equals("CN")) {	//i 다음 역을 폐쇄, 폐쇄된 역의 고유 번호 출력		: i -> i 다음역의 다음역, i 다음역의 다음역 업데이트
        		int i = Integer.parseInt(tokens[1]);
        		int iPost = postArr[i];
        		int iPostPost = postArr[iPost];
        		
        		postArr[i] = iPostPost;
        		preArr[iPostPost] = i;
        		bw.write(iPost+"\n");        		
        	}else if(command.equals("CP")) {	//i 이전 역을 폐쇄, 폐쇄된 역의 고유 번호 출력		: i 이전역의 이전역, i 업데이트
        		int i = Integer.parseInt(tokens[1]);
        		int iPrev = preArr[i];
        		int iPrevPrev = preArr[iPrev];
        		
        		postArr[iPrevPrev] = i;
        		preArr[i] = iPrevPrev;
        		
        		bw.write(iPrev+"\n");
        	}
    	}
    	
        bw.flush();
        bw.close();
        br.close();
    }
}
