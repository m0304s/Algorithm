import java.util.*;
import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class Baseball{
        private int first;
        private int second;
        private int third;
        private int strike;
        private int ball;

        public Baseball(int first,int second, int third, int strike, int ball){
            this.first = first;
            this.second = second;
            this.third = third;
            this.strike = strike;
            this.ball = ball;
        }
        public String toString(){
            return "First : " + first + " Second : " + second + " third : " + third + " strike : " + strike + " ball : " + ball;
        }
        public int getFirst(){
            return this.first;
        }
        public int getSecond(){
            return this.second;
        }
        public int getThird(){
            return this.third;
        }
        public int getStrike(){
            return this.strike;
        }
        public int getBall(){
            return this.ball;
        }
    }

    public static ArrayList<Baseball> baseballs = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            int inputNum = Integer.parseInt(tokens[0]);
            int strike = Integer.parseInt(tokens[1]);
            int ball = Integer.parseInt(tokens[2]);

            baseballs.add(new Baseball(inputNum/100, (inputNum/10)%10, inputNum%10, strike, ball));
        }

        int answer = 0;
        for(int i=123;i<=987; i++){
            int firstValue = i / 100;
            int secondValue = (i/10) % 10;
            int thirdValue = i % 10;
            // 숫자에 0이 포함되거나 중복된 경우 제외
            if (firstValue == 0 || secondValue == 0 || thirdValue == 0 ||
            firstValue == secondValue || firstValue == thirdValue || secondValue == thirdValue) continue;   //0이거나 같은 숫자가 나타날 경우 경우의 수에서 제외

            answer += checkAnswer(firstValue,secondValue,thirdValue);
        }
        bw.write(answer+"\n");
        bw.flush();
    }
    public static int checkAnswer(int firstValue,int secondValue,int thirdValue){
        for(int i=0;i<baseballs.size();i++){
            int strikeCnt = 0;
            int ballCnt = 0;
            Baseball baseball = baseballs.get(i);   //비교 할 번호
            if(firstValue == baseball.getFirst()){
                strikeCnt++;
            }else if(firstValue == baseball.getSecond() || firstValue == baseball.getThird()){
                ballCnt++;
            }
            if(secondValue == baseball.getSecond()){
                strikeCnt++;
            }else if(secondValue == baseball.getFirst() || secondValue == baseball.getThird()){
                ballCnt++;
            }
            if(thirdValue == baseball.getThird()){
                strikeCnt++;
            }else if(thirdValue == baseball.getFirst() || thirdValue == baseball.getSecond()){
                ballCnt++;
            }
            // 스트라이크와 볼의 개수가 예상 값과 다를 경우 답이 될 수 없음
            if(strikeCnt != baseball.getStrike() || ballCnt != baseball.getBall()){
                return 0;
            }
        }
        return 1;
    }
}