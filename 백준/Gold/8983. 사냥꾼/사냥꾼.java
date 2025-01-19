import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int M,N,L;
    private static List<Integer> sadae;
    private static List<Animal> animalList;

    private static class Animal{
        int x,y;
        public Animal(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String [] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        sadae = new ArrayList<Integer>();
        animalList = new ArrayList<Animal>();
        M = Integer.parseInt(tokens[0]);
        N = Integer.parseInt(tokens[1]);
        L = Integer.parseInt(tokens[2]);

        tokens = br.readLine().split(" ");
        for(int i=0;i<M;i++) {
            int x = Integer.parseInt(tokens[i]);
            sadae.add(x);
        }

        for(int i=0;i<N;i++) {
            tokens = br.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            animalList.add(new Animal(x,y));
        }

        Collections.sort(animalList, new Comparator<Animal>(){  //X축 기준으로 내림차순 정렬
            public int compare(Animal a1, Animal a2){
                return a1.x - a2.x;
            }
        });

        int count = 0;
        for(Animal animal : animalList){
            //이진탐색을 통해 동물의 x좌표와 가장 가까운 사대 탐색
            count+=binaraySearch(animal);
        }

        bw.write(count+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int binaraySearch(Animal animal){
        int left = 0;
        int right = sadae.size()-1;

        if(animal.y > L) return 0;

        while(left <= right){
            int mid = (left + right) / 2;
            int sadaeX = sadae.get(mid);

            //사냥 가능 여부 확인
            if(Math.abs(sadaeX - animal.x) + animal.y <= L){
                return 1;
            }

            if(sadaeX < animal.x){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return 0;
    }
}