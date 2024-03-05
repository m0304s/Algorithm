import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int x = kb.nextInt();   //Month
        int y = kb.nextInt();   //Day

        int daySum=0;   //목표 날짜까지 일 수
        daySum=calculate(daySum,x, y);
        switch (daySum%7) {
            case 0:
                System.out.println("MON");
                break;
            case 1:
                System.out.println("TUE");
                break;
            case 2:
                System.out.println("WED");
                break;
            case 3:
                System.out.println("THU");
                break;
            case 4:
                System.out.println("FRI");
                break;
            case 5:
                System.out.println("SAT");
                break;
            case 6:
                System.out.println("SUN");
                break;
            default:
                break;
        }
        kb.close();
    }
    public static int calculate(int daySum,int x,int y){
        for(int i=1;i<=x-1;i++){
            if(i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12){
                daySum+=31;
            }else if(i==2){
                daySum+=28;
            }else{
                daySum+=30;
            }
        }
        daySum+=y;
        return daySum-1;
    }
}
