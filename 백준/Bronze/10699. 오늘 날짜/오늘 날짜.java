import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	private static final int [] dx = {0,0,-1,1};
	private static final int [] dy = {-1,1,0,0};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String [] args) throws IOException{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		System.out.println(now.format(format));
	}
}

