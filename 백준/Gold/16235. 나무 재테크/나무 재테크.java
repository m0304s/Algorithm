import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M,K;

    static class Tree{
        int age;

        public int getAge() {
            return age;
        }

        public Tree(int age){
            this.age = age;
        }

        public void increaseAge(){
            this.age++;
        }
    }

    static class Earth{
        int earthX,earthY,earthNutrition;
        List<Tree> aliveTreeList;
        List<Tree> deadTreeList;

        public Earth(int earthX, int earthY){
            this.earthX = earthX;
            this.earthY = earthY;
            this.earthNutrition = 5;
            this.aliveTreeList = new ArrayList<>();
            this.deadTreeList = new ArrayList<>();
        }
        public void decreaseNutrition(int earthNutrition){
            this.earthNutrition -= earthNutrition;
        }
        public int getEarthNutrition() {
            return earthNutrition;
        }

        public void setEarthNutrition(int earthNutrition) {
            this.earthNutrition = earthNutrition;
        }

        public List<Tree> getDeadTreeList() {
            return deadTreeList;
        }

        public void setDeadTreeList(List<Tree> deadTreeList) {
            this.deadTreeList = deadTreeList;
        }

        public List<Tree> getAliveTreeList() {
            return aliveTreeList;
        }

        public void setAliveTreeList(List<Tree> aliveTreeList) {
            this.aliveTreeList = aliveTreeList;
        }

        public int getEarthX() {
            return earthX;
        }

        public void setEarthX(int earthX) {
            this.earthX = earthX;
        }

        public int getEarthY() {
            return earthY;
        }

        public void setEarthY(int earthY) {
            this.earthY = earthY;
        }

        public void addNutrition(int nutrition) {
            this.earthNutrition += nutrition;
        }
    }

    static Earth[][] earths;
    static int [][] additionalNutritions;

    public static void main(String[] args) throws IOException {
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        K = Integer.parseInt(tokens[2]);

        earths = new Earth[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                earths[i][j] = new Earth(i, j);
            }
        }
        initializeAdditionalNutrition();
        initializeTrees();
        simulation();
        printResult();
    }

    private static void simulation() {
        for(int k=0;k<K;k++){
            spring();
            summer();
            autumn();
            winter();
        }
    }

    private static void winter() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                earths[i][j].addNutrition(additionalNutritions[i][j]);
            }
        }
    }

    /**
     * 나무가 번식한다.
     * 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
     * 어떤 칸 (r, c)와 인접한 칸은(r-1, c-1),(r-1, c),(r-1, c+1),(r, c-1),(r, c+1),(r+1, c-1),(r+1, c),(r+1, c+1)이다.
     * 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
     */
    private static void autumn() {
        int [] dx = {-1,-1,-1,0,1,1,1,0};
        int [] dy = {-1,0,1,1,1,0,-1,-1};

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                Earth position = earths[i][j];

                List<Tree> momTreeList = new ArrayList<>();
                for(Tree tree : position.getAliveTreeList()){
                    if(tree.getAge() % 5 == 0) momTreeList.add(tree);
                }

                for (Tree tree : momTreeList) {
                    for(int d=0;d<8;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(!inRange(nx,ny)) continue;

                        earths[nx][ny].getAliveTreeList().add(new Tree(1));
                    }
                }
            }
        }
    }

    private static boolean inRange(int x,int y){
        return x>=0 && x < N && y >= 0 && y < N;
    }

    private static void printResult() throws IOException{
        int totalTrees = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                totalTrees+=earths[i][j].getAliveTreeList().size();
            }
        }
        bw.write(totalTrees+"\n");
        bw.flush();
        br.close();
        bw.close();
    }

    /**
     * 봄에 죽은 나무가 양분으로 변하게 된다.
     * 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
     * 소수점 아래는 버린다.
     */
    private static void summer() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                Earth position = earths[i][j];

                List<Tree> deadTrees = position.getDeadTreeList();
                for (Tree deadTree : deadTrees) {
                    position.addNutrition(deadTree.getAge() / 2);
                }
                position.getDeadTreeList().clear();    //죽은 나무 목록 초기화
            }
        }
    }

    /**
     * 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
     * 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
     * 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
     * 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
     */
    private static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Earth position = earths[i][j];

                List<Tree> aliveTrees = position.getAliveTreeList();
                aliveTrees.sort(Comparator.comparingInt(Tree::getAge));

                List<Tree> deadTrees = new ArrayList<>();
                int nutrition = position.getEarthNutrition();

                List<Tree> survivedTrees = new ArrayList<>();
                for (Tree tree : aliveTrees) {
                    if (nutrition >= tree.getAge()) {
                        nutrition -= tree.getAge();
                        tree.increaseAge();
                        survivedTrees.add(tree);
                    } else {
                        deadTrees.add(tree);
                    }
                }

                // 상태 갱신
                position.setAliveTreeList(survivedTrees); // 생존 나무
                position.setDeadTreeList(deadTrees);      // 죽은 나무
                position.setEarthNutrition(nutrition);   // 남은 양분
            }
        }
    }


    private static void initializeAdditionalNutrition() throws IOException{
        additionalNutritions = new int[N][N];
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                additionalNutritions[i][j] = Integer.parseInt(tokens[j]);
            }
        }
    }

    private static void initializeTrees() throws IOException{
        for(int i=0;i<M;i++){
            String [] tokens = br.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            int age = Integer.parseInt(tokens[2]);

            earths[x-1][y-1].getAliveTreeList().add(new Tree(age));
        }
    }
}
