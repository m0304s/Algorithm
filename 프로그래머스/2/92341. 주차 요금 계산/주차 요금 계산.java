import java.util.*;

class Solution {
    class Car{
        String number;      //차량번호
        int inputTime;      //입차시간
        int outputTime;     //출차시간
        int cumTime;        //누적시간
        boolean status;     //주차장에 들어와있는지 여부
        
        public Car(String number,int inputTime){
            this.number = number;
            this.inputTime = inputTime;
            this.status = true;
            this.outputTime = 0;
            this.cumTime = 0;
        }
        public String toString(){
            return "Number : " + this.number + " InputTime : " + this.inputTime + " OutputTime : " + this.outputTime + " CumTime : " + this.cumTime + " Status : " + this.status;
        }
    }
    public ArrayList<Car> carList = new ArrayList<>(); //자동차를 저장하는 배열
    
    public boolean checkNumber(String number){            //carList에 자동차가 존재하는지 체크하는 함수
        for(int i=0;i<carList.size();i++){
            Car car = carList.get(i);
            if(car.number.equals(number)){
                return true;
            }
        }
        return false;
    }
    public int[] convertTime(String time){
        String [] tokens = time.split(":");
        int [] returnValue = new int[2];
        returnValue[0] = Integer.parseInt(tokens[0]);
        returnValue[1] = Integer.parseInt(tokens[1]);
        
        return returnValue;
    }
    
    public int exchangeTime(int [] hours){
        return 60 * hours[0] + hours[1];
    }
    
    public ArrayList<Integer> solution(int[] fees, String[] records) {
        for(int i=0;i<records.length;i++){
            String [] tokens = records[i].split(" ");
            String carNum = tokens[1];
            String recordType = tokens[2];
            Car car = null;
            if(recordType.equals("IN")){    //입차
                int [] hours = convertTime(tokens[0]);  //입차시간
                if(checkNumber(carNum)){    //기존에 입차한 기록이 있을 경우
                    int carIdx = 0;
                    for(int j=0;j<carList.size();j++){
                        if(carList.get(j).number.equals(carNum)){
                            carIdx = j;
                            break;
                        }
                    }
                    car = carList.get(carIdx);  //입차한 차량
                    car.inputTime = exchangeTime(hours);
                    car.status = true;
                }else{  //기존에 입차한 기록이 없을 경우
                    car = new Car(carNum,exchangeTime(hours));
                    carList.add(car);
                }
            }else{  //출차
                int carIdx = 0;
                int [] hours = convertTime(tokens[0]);  //출차시간
                for(int j=0;j<carList.size();j++){
                    if(carList.get(j).number.equals(carNum)){
                        carIdx = j;
                        break;
                    }
                }
                car = carList.get(carIdx);  //출차하는 차량
                car.status = false;
                car.outputTime = exchangeTime(hours);
                car.cumTime += car.outputTime - car.inputTime;    //누적시간 갱신
            }
        }
        for(int i=0;i<carList.size();i++){
            if(carList.get(i).status){ //주차장에 차량이 남아있는 경우
                String outTime = "23:59";
                Car car = carList.get(i);
                car.outputTime = exchangeTime(convertTime(outTime));
                car.cumTime += car.outputTime - car.inputTime;
                car.status = false;
            }
        }
        
        //CarList를 차량번호가 작은 순으로 정렬
        Collections.sort(carList, new Comparator<Car>() {
            public int compare(Car c1, Car c2) {
                // 문자열을 사전 순으로 비교
                return c1.number.compareTo(c2.number);
            }
        });
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        for(int i=0;i<carList.size();i++){
            Car car = carList.get(i);
            if(car.cumTime <= baseTime){
                answer.add(baseFee);
            }else{
                int extraTime = car.cumTime - baseTime;
                int extraFee = (int) Math.ceil((double) extraTime / unitTime) * unitFee;
                int totalFee = baseFee + extraFee;
                answer.add(totalFee);
            }
        }
        return answer;
    }
}