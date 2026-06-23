class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int numberOfPeople = schedules.length;
        
        for (int i = 0; i < numberOfPeople; i++) {
            int desiredTime = schedules[i];
            
            int deadLineMinutes = (desiredTime % 100) + 10;
            int deadLineHours = desiredTime / 100;
            
            if (deadLineMinutes >= 60) {
                deadLineHours += 1;
                deadLineMinutes -= 60;
            }
            int deadLine = (deadLineHours * 100) + deadLineMinutes;
            
            boolean available = true;
            
            for (int j = 0; j < 7; j++) {
                int dayOfTheWeek = (startday + j) % 7;
                
                if (dayOfTheWeek == 6 || dayOfTheWeek == 0) {
                    continue;
                }
                
                if (timelogs[i][j] > deadLine) {
                    available = false;
                    break;
                }
            }
            
            if (available) {
                answer++;
            }
        }
        
        return answer;
    }
}