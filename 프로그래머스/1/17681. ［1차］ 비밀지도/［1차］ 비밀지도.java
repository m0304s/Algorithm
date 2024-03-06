import java.util.*;

class Solution {
    public ArrayList<String> solution(int n, int[] arr1, int[] arr2) {
        String [][] map = new String [n][n];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                map[i][j] = " ";
            }
        }
        for(int i=0;i<arr1.length;i++){
            int num = arr1[i];
            ArrayList<Integer> list = new ArrayList<>();
            while(num!=0){
                list.add(num%2);
                num = num/2;
            }
            while(list.size()!=n){
                list.add(0);
            }
            for(int j=list.size()-1;j>=0;j--){
                if(list.get(j) == 1){
                    map[i][j] = "#";
                }
            }
        }
        for(int i=0;i<arr2.length;i++){
            int num = arr2[i];
            ArrayList<Integer> list = new ArrayList<>();
            while(num!=0){
                list.add(num%2);
                num = num/2;
            }
            
            while(list.size()!=n){
                list.add(0);
            }
            for(int j=list.size()-1;j>=0;j--){
                if(list.get(j) == 1){
                    map[i][j] = "#";
                }
            }
        }
        ArrayList<String> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<map.length;i++){
            for(int j=map[0].length-1;j>=0;j--){
                sb.append(map[i][j]);
            }
            answer.add(sb.toString());
            sb.setLength(0);
        }
        return answer;
    }
}