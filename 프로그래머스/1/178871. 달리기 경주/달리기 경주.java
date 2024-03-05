import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String,Integer> map = initialize(players);
        for(String calledName : callings){
            int calledRank = map.get(calledName);
            changeRank(calledName,calledRank,players,map);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).getKey();
        }
        return answer;
    }
private void changeRank(String calledName, int calledRank, String[] players, Map<String, Integer> map) {
        String front = players[calledRank-1];
        players[calledRank-1]=calledName;
        players[calledRank]=front;
        map.put(front, calledRank);
        map.put(calledName, calledRank-1);
    }
 private Map<String,Integer> initialize(String[]players){
        Map<String,Integer> map = new HashMap<String,Integer>();
        for(int i=0;i<players.length;i++){
            map.put(players[i], i);
        }
        return map;
    }
}
