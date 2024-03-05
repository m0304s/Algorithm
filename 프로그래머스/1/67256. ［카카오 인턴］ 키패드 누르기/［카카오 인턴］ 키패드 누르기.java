import java.util.*;
import java.io.*;

class Solution {
    public static BufferedWriter br = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int [][] handPosition = new int[4][3];    //현재 손의 위치를 저장
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        handPosition[3][0]=1;   //1: 왼손
        handPosition[3][2]=2;   //2: 오른손
        
        for(int number: numbers){
            if(number==1 || number==4 || number==7){
                answer+="L";
                for(int i=0;i<handPosition.length;i++){
                    for(int j=0;j<handPosition[0].length;j++){
                        if(handPosition[i][j]!=2){
                            handPosition[i][j]=0;
                        }
                    }
                }
                handPosition[number/3][0]=1;
            }else if(number==3 || number==6 || number==9){
                answer+="R";
                for(int i=0;i<handPosition.length;i++){
                    for(int j=0;j<handPosition[0].length;j++){
                        if(handPosition[i][j]!=1){
                            handPosition[i][j]=0;
                        }
                    }
                }
                handPosition[number/3-1][2]=2;
            }else{
                int x=0;
                int y=0;
                switch(number){
                    case 2:
                        x=0;
                        y=1;
                        break;
                    case 5:
                        x=1;
                        y=1;
                        break;
                    case 8:
                        x=2;
                        y=1;
                        break;
                    case 0:
                        x=3;
                        y=1;
                        break;
                }
                int distance_x=0;
                int distance_y=0;
                
                int left_x=0;
                int left_y=0;
                int right_x=0;
                int right_y=0;
                
                for(int i=0;i<handPosition.length;i++){
                    for(int j=0;j<handPosition[0].length;j++){
                        if(handPosition[i][j]==1){
                            distance_x=Math.abs(i-x)+Math.abs(j-y);
                            left_x=i;
                            left_y=j;
                        }else if(handPosition[i][j]==2){
                            distance_y=Math.abs(i-x)+Math.abs(j-y);
                            right_x=i;
                            right_y=j;
                        }
                    }
                }
                System.out.println("Distance_x: "+distance_x+" Distance_y: "+distance_y);
                if(distance_x>distance_y){  //엄지손가락 이용
                    answer+="R";
                    for(int i=0;i<handPosition.length;i++){
                        for(int j=0;j<handPosition[0].length;j++){
                            if(handPosition[i][j]!=1){
                                handPosition[i][j]=0;
                            }
                        }
                    }
                    handPosition[x][y]=2;
                }else if(distance_x<distance_y){    //왼손가락 이용
                    answer+="L";
                    for(int i=0;i<handPosition.length;i++){
                        for(int j=0;j<handPosition[0].length;j++){
                            if(handPosition[i][j]!=2){
                                handPosition[i][j]=0;
                            }
                        }
                    }
                    handPosition[x][y]=1;
                }else{
                    //서로 거리가 같을 경우-> 주사용손에 맞춤
                    if(hand.equals("right")){
                        answer+="R";
                        for(int i=0;i<handPosition.length;i++){
                            for(int j=0;j<handPosition[0].length;j++){
                                if(handPosition[i][j]!=1){
                                    handPosition[i][j]=0;
                                }
                            }
                        }
                        handPosition[x][y]=2;   
                    }else{
                        answer+="L";
                        for(int i=0;i<handPosition.length;i++){
                            for(int j=0;j<handPosition[0].length;j++){
                                if(handPosition[i][j]!=2){
                                    handPosition[i][j]=0;
                                }
                            }
                        }
                        handPosition[x][y]=1;   
                    }
                }
            }
            System.out.println("===============");
            for(int i=0;i<handPosition.length;i++){
                    for(int j=0;j<handPosition[0].length;j++){
                        System.out.print(handPosition[i][j]+" ");
                    }System.out.println();
                }
        }
        return answer;
    }
}