import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        boolean[] isSpoilerIdx = new boolean[message.length()];
        
        // 스포일러 구간 마킹
        for (int[] range : spoiler_ranges) {
            int start = range[0];
            int end = range[1];
            for (int i = start; i <= end; i++) {
                isSpoilerIdx[i] = true;
            }
        }
        
        // 스포일러가 아닌 일반 구간에 한 번이라도 등장한 단어들을 저장
        Set<String> disqualified = new HashSet<>();
        
        StringBuilder currentWord = new StringBuilder();
        boolean isCurrentWordSpoiler = false;
        
        for (int i = 0; i <= message.length(); i++) {
            if (i == message.length() || message.charAt(i) == ' ') {
                if (currentWord.length() > 0) {
                    // 단어 중 단 한 글자도 스포일러가 아니었다면 일반 구간 등장 단어
                    if (!isCurrentWordSpoiler) {
                        disqualified.add(currentWord.toString());
                    }
                    currentWord.setLength(0);
                    isCurrentWordSpoiler = false;
                }
            } else {
                currentWord.append(message.charAt(i));
                if (isSpoilerIdx[i]) {
                    isCurrentWordSpoiler = true;
                }
            }
        }
        
        // 왼쪽부터 순서대로 탐색하며 중요한 단어 카운트
        Set<String> seenSpoilers = new HashSet<>(); // 이미 공개된 스포 방지 단어 체크용
        int cnt = 0;
        
        currentWord.setLength(0);
        isCurrentWordSpoiler = false;
        
        for (int i = 0; i <= message.length(); i++) {
            if (i == message.length() || message.charAt(i) == ' ') {
                if (currentWord.length() > 0) {
                    String wordStr = currentWord.toString();
                    
                    // 스포일러 단어인 경우만 판정 진행
                    if (isCurrentWordSpoiler) {
                        if (!seenSpoilers.contains(wordStr)) {
                            if (!disqualified.contains(wordStr)) {
                                cnt++;
                            }
                            seenSpoilers.add(wordStr); // 공개 처리
                        }
                    }
                    currentWord.setLength(0);
                    isCurrentWordSpoiler = false;
                }
            } else {
                currentWord.append(message.charAt(i));
                if (isSpoilerIdx[i]) {
                    isCurrentWordSpoiler = true;
                }
            }
        }
        
        return cnt;
    }
}