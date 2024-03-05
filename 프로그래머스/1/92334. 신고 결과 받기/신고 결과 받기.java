import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        HashMap<String, ArrayList<String>> reportMap = new HashMap<>();
        HashMap<String, Integer> reportCount = new HashMap<>();
        // 신고 내역 맵에 저장
        for (String r : report) {
            String[] array = r.split(" ");
            if (reportMap.containsKey(array[0])) {
                ArrayList<String> inputList = reportMap.get(array[0]);
                inputList.add(array[1]);
            } else {
                ArrayList<String> inputList = new ArrayList<>();
                inputList.add(array[1]);
                reportMap.put(array[0], inputList);
            }
        }
        
        //동일 유저가 처리한 것은 중복 처리
        for (String id : reportMap.keySet()) {
            ArrayList<String> list = reportMap.get(id);
            HashSet<String> set = new HashSet<>(list);
            list.clear();
            list.addAll(set);
        }
        
        for(String id: reportMap.keySet()){
            ArrayList<String> list = reportMap.get(id);
            for(String name: list){
                if(!reportCount.containsKey(name)){
                    reportCount.put(name,1);
                }else{
                    reportCount.put(name,reportCount.get(name)+1);
                }
            }
        }
                
        
        ArrayList<String> stopped = new ArrayList<>();
        // 신고 내역에 따라 처리
        for(String id: reportCount.keySet()){
            int reportCnt = reportCount.get(id);
            if(reportCnt>=k){    //정지
                stopped.add(id);
            }
        }
        
        HashMap<String, Integer> answerMap = new HashMap<>();
        for(int i=0;i<id_list.length;i++){
            answerMap.put(id_list[i],0);
        }
        for(String id: stopped){
            for(String name: reportMap.keySet()){
                ArrayList<String> list = reportMap.get(name);
                for(String s : list){
                    if(s.equals(id)){   //만약 신고한 유저가 있을 경우
                        answerMap.put(name, answerMap.get(name)+1);
                    }
                }
            }
        }
        
        for(int i=0;i<id_list.length;i++){
            answer[i]=answerMap.get(id_list[i]);
        }
        return answer;
    }
}
