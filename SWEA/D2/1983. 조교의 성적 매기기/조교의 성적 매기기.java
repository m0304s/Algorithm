import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Student implements Comparable<Student>{
        int index;
        int midScore;
        int finalScore;
        int termScore;
        double totalScore;

        public Student(int index, int midScore, int finalScore, int termScore){
            this.index = index;
            this.midScore = midScore;
            this.finalScore = finalScore;
            this.termScore = termScore;
            this.totalScore = (midScore) * 0.35 + (finalScore) * 0.45 + termScore * (0.2);
        }
        public int compareTo(Student o){
            return Double.compare(o.totalScore, this.totalScore);
        }

        public String toString(){
            return "Index : " + this.index + " MidScore : " + this.midScore + " FinalScore : " + this.finalScore + " TermScore : " + this.termScore + " TotalScore : " + this.totalScore;
        }
    }

    static int N,K;
    static List<Student> studentList;
    static String [] grade = new String[]{"A+","A0","A-","B+","B0","B-","C+","C0","C-","D0"};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            K = Integer.parseInt(tokens[1]);
            studentList = new ArrayList<>();

            for(int i=0;i<N;i++){
                tokens = br.readLine().split(" ");
                int midTerm = Integer.parseInt(tokens[0]);
                int finalTerm = Integer.parseInt(tokens[1]);
                int termScore = Integer.parseInt(tokens[2]);

                studentList.add(new Student(i+1,midTerm,finalTerm,termScore));
            }
            Collections.sort(studentList);
            int calculatedGrade = calculateGrade();
            bw.write("#"+t+" "+grade[calculatedGrade]+"\n");
            bw.flush();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int calculateGrade(){
        int studentPerGrade = N / 10;

        int studentOrder = 0;
        for(int i=0;i<studentList.size();i++){
            if(studentList.get(i).index == K){
                studentOrder = i;
                break;
            }
        }
        int gradeIndex = studentOrder/studentPerGrade;

        return gradeIndex;
    }
}
