import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int solution(int[] ingredient) {
        int answer=0;
        ArrayList<Integer> ingredientList = new ArrayList<>(
            Arrays.stream(ingredient).boxed().collect(Collectors.toList())
        );

        for(int i=0;i<ingredientList.size()&&ingredientList.size()>3;i++){
            if(ingredientList.get(i)==1){
                if (i > 2) {
                    if (ingredientList.get(i-1) == 3 && 
                        ingredientList.get(i-2) == 2 &&
                        ingredientList.get(i-3) == 1) {
                        ingredientList.remove(i);
                        ingredientList.remove(i-1);
                        ingredientList.remove(i-2);
                        ingredientList.remove(i-3);

                        answer++;

                        i -= 4; // 4칸 전으로 간다. 다음 루프 때 for 증감문(i++)으로 인해 1칸 뒤로 간다.

                        continue;
                        }
                    }
            }
        }
        System.out.println(answer);
        return answer;
    }
}