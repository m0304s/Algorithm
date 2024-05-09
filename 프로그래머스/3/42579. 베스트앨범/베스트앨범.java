import java.util.*;

class Solution {
    class Song{
        int index;
        int play;
        public Song(int index, int play){
            this.index = index;
            this.play = play;
        }
        public String toString(){
            return index+" "+play;
        }
    }
    public static HashMap<String, ArrayList<Song>> map = new HashMap<>();
    public static HashMap<String, Integer> genrecnt = new HashMap<>();

    public ArrayList<Integer> solution(String[] genres, int[] plays) {
        for(int i=0;i<genres.length;i++){
            if(map.containsKey(genres[i])){
                ArrayList<Song> list = map.get(genres[i]);
                list.add(new Song(i,plays[i]));
                genrecnt.put(genres[i],genrecnt.get(genres[i])+plays[i]);
            }else{
                genrecnt.put(genres[i],plays[i]);
                Song s = new Song(i,plays[i]);
                ArrayList<Song> list = new ArrayList<>();
                list.add(s);
                map.put(genres[i],list);
            }
        }
        Set<String> keyset = map.keySet();
        for(String s : keyset){
            Collections.sort(map.get(s),new Comparator<Song>(){
                public int compare(Song s1, Song s2){
                    return s2.play-s1.play;
                }
            });
        }
        Set<Map.Entry<String, Integer>> entrySet = genrecnt.entrySet();
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(entrySet);
        
        Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });

        ArrayList<Integer> answer = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : sortedList){
            String genre = entry.getKey();
            ArrayList<Song> answerList = map.get(genre);
            for(int i=0;i<answerList.size();i++){
                if(i>=2){
                    break;
                }else{
                    Song s = answerList.get(i);
                    answer.add(s.index); 
                }
            }
        }
        return answer;
    }
}
