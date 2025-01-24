import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N,M;
    private static int [] people;
    private static List<Set<Integer>> partyList;
    public static void main(String [] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        partyList = new ArrayList<>();

        for(int i=0;i<M;i++){
            partyList.add(new HashSet<>());
        }

        people = new int[N+1];

        for(int i=0;i<N+1;i++){
            people[i] = i;
        }

        tokens = br.readLine().split(" ");
        int numOfPeopleKnowTruth = Integer.parseInt(tokens[0]); //진실을 아는 사람 수
        if(numOfPeopleKnowTruth == 0){
            bw.write(M+"\n");
            bw.flush();
            return;
        }
        int startPeople = Integer.parseInt(tokens[1]);
        for(int i=1;i<tokens.length;i++){
            int peopleNum = Integer.parseInt(tokens[i]);
            people[peopleNum] = startPeople;
        }

        for(int i=0;i<M;i++){
            tokens = br.readLine().split(" ");
            int partyPeople = Integer.parseInt(tokens[0]);
            int startPeopleOfParty = Integer.parseInt(tokens[1]);
            for(int j=1;j<=partyPeople;j++){
                union(startPeopleOfParty,Integer.parseInt(tokens[j]));
                partyList.get(i).add(Integer.parseInt(tokens[j]));
            }
        }

        HashSet<Integer> cantalkPeople = new HashSet<>();
        for(int i=1;i<people.length;i++){
            if(people[i] != startPeople){
                cantalkPeople.add(i);
            }
        }
        int partyCount = 0;

        for (int i = 0; i < M; i++) {
            boolean canLie = true;
            for (int person : partyList.get(i)) {
                if (find(person) == find(startPeople)) {  // 진실을 아는 그룹과 연결된 사람이 있으면
                    canLie = false;
                    break;
                }
            }
            if (canLie) {
                partyCount++;
            }
        }

        bw.write(partyCount+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int a,int b){
        int rootAIdx = find(a);        //a노드의 root value
        int rootBIdx = find(b);        //b노드의 root value

        if(people[rootAIdx] == people[rootBIdx]) return;  //같은 그래프일 경우 탐색 중단

        if(people[rootAIdx] <= people[rootBIdx]){
            people[rootBIdx] = people[rootAIdx];
        }else{
            people[rootAIdx] = people[rootBIdx];
        }
    }

    private static int find(int idx){
        if(people[idx] == idx) return idx;

        return people[idx] = find(people[idx]);
    }
}
