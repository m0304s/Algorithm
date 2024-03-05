import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        double total_point = 0; //전체학점
        double add = 0; //학점*과목평점
        for(int i=0;i<20;i++){
            String object = kb.next();
            double point = kb.nextDouble();
            String grade = kb.next();
            if(grade.equals("P")){
                continue;
            }
            total_point+=point;

            double switchgrade = switchDouble(grade);   //변환된 점수
            add+=point*switchgrade;
        }
        System.out.println(add/total_point);
    }
    public static double switchDouble(String score) {
        double returnscore=0;
        switch(score){
            case "A+":
                returnscore= 4.5;
                break;
            case "A0":
                returnscore=4;
                break;
            case "B+":
                returnscore=3.5;
                break;
            case "B0":
                returnscore=3;
                break;

            case "C+":
                returnscore=2.5;
                break;

            case "C0":
                returnscore=2;
                break;


            case "D+":
                returnscore=1.5;
                break;

            case "D0":
                returnscore=1;
                break;

            case "F":
                returnscore=0.0;
                break;
        }
        return returnscore;
    }
}
