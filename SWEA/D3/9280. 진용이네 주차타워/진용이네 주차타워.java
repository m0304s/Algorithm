import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M;
    static boolean [] slotStatus;   //주차장 공간 상태
    static int [] costPerWeights;   //단위 무게 당 요금

    static class Car{
        int number,weight,slotNum;
        public Car(int number,int weight){
            this.number = number;
            this.weight = weight;
            this.slotNum = -1;
        }

        public int getWeight() {
            return weight;
        }

        public int getNumber() {
            return number;
        }

        public void setSlotNum(int slotNum){
            this.slotNum = slotNum;
        }
        public int getSlotNum(){
            return this.slotNum;
        }
    }
    static Queue<Car> waitingQueue;
    static List<Car> carList;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            String [] tokens = br.readLine().split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);
            carList = new ArrayList<>();
            waitingQueue = new LinkedList<>();

            slotStatus = new boolean[N+1];
            costPerWeights = new int[N+1];

            for(int i=1;i<=N;i++){
                costPerWeights[i] = Integer.parseInt(br.readLine());
            }
            for(int i=1;i<=M;i++){
                carList.add(new Car(i,Integer.parseInt(br.readLine())));
            }

            int totalCost = 0;

            for(int i=1;i<=2*M;i++){
                int carNum = Integer.parseInt(br.readLine());
                if(carNum > 0){     //차량이 들어올 경우
                    if(hasFindEmptySlot()){
                        if(waitingQueue.isEmpty()){     //대기 차량이 없을 경우
                            int slotNum = findMinSlotNumber();
                            slotStatus[slotNum] = true;
                            carList.get(carNum-1).setSlotNum(slotNum);
                        }else{                          //대기 차량이 있을 경우
                            waitingQueue.add(carList.get(carNum));
                            int slotNum = findMinSlotNumber();
                            slotStatus[slotNum] = true;
                            Car car = waitingQueue.poll();
                            carList.get(car.getNumber()-1).setSlotNum(slotNum);
                        }
                    }else{
                        waitingQueue.add(carList.get(carNum-1));
                    }
                }else{              //차량이 나가는 경우
                    int carNumAbs = Math.abs(carNum)-1;
                    Car outCar = carList.get(carNumAbs);
                    slotStatus[outCar.getSlotNum()] = false;
                    totalCost+=outCar.getWeight()*costPerWeights[outCar.getSlotNum()];

                    //대기하고 있던 차량이 있을 경우
                    if(!waitingQueue.isEmpty()){
                        Car car = waitingQueue.poll();
                        slotStatus[outCar.getSlotNum()] = true;
                        carList.get(car.getNumber()-1).setSlotNum(outCar.getSlotNum());
                    }
                }
            }
            bw.write("#"+t+" "+totalCost+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean hasFindEmptySlot(){
        for(int i=1;i<slotStatus.length;i++){
            if(!slotStatus[i]) return true;
        }
        return false;
    }

    private static int findMinSlotNumber(){
        for(int i=1;i<slotStatus.length;i++){
            if(!slotStatus[i]) return i;
        }
        throw new IllegalStateException();
    }

    private static Car findCar(int number){
        for (Car car : carList) {
            if(car.getNumber() == number) return car;
        }
        return null;
    }
}
