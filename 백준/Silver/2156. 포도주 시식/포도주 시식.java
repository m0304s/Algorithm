import java.util.*;
import java.io.*;
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> oneStep = new ArrayList<>();
    public static ArrayList<Integer> twoStep = new ArrayList<>();
    public static ArrayList<Integer> threeStep = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int [] score = new int[N];
        for(int i=0;i<N;i++){
            score[i]=Integer.parseInt(br.readLine());
        }
        //0단계
        oneStep.add(0);
        twoStep.add(0);
        threeStep.add(0);
        //1단계
        oneStep.add(0);
        twoStep.add(score[0]);
        threeStep.add(score[0]);

        for(int i=1;i<N;i++){
            int oneStepMax = Math.max(Math.max(oneStep.get(oneStep.size()-1),twoStep.get(twoStep.size()-1)),threeStep.get(threeStep.size()-1));
            int twoStepMax = oneStep.get(oneStep.size()-1)+score[i];
            int threeStepMax = twoStep.get(twoStep.size()-1)+score[i];

            oneStep.add(oneStepMax);
            twoStep.add(twoStepMax);
            threeStep.add(threeStepMax);
        }
        int max = Math.max(Math.max(oneStep.get(oneStep.size()-1),twoStep.get(twoStep.size()-1)),threeStep.get(threeStep.size()-1));
        bw.write(max+"\n");
        bw.flush();
        bw.close();
    }
}
