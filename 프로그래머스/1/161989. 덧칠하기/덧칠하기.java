import java.util.Arrays;
class Solution {
public int solution(int n, int m, int[] section) {
        final int Colored = 1;  //색이 칠해진 부분은 1로 표시
        final int Blanked = 0;  //색이 칠해진 부분은 1로 표시
        int answer = 0;
        int [] wall = new int[n];
        int [] wish = new int[n];
        Arrays.fill(wish, Colored);
        Arrays.fill(wall,Colored);
        int first_point = section[0]-1; //첫번째 칠해야 하는 부분 ex) 2번째 구역 -> first_point = 1
        for(int i=0;i<section.length;i++){
            wall[section[i]-1]=Blanked;
        }
        while(!Arrays.equals(wall, wish)){
            int point = first_point;
            for(int i=0;i<m;i++){
                if(i+point>=wall.length){
                    break;
                }
                wall[i+point]=Colored;
            }
            first_point=find_point(wall);
            answer++;
        }
        return answer;
    }
    int find_point(int [] array){
        for(int i=0;i<array.length;i++){
            if(array[i]!=1){
                return i;
            }
        }
        return 0;
    }
}