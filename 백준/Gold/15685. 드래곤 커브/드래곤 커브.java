import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;

    static class Node{
        int x,y;

        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static class DragonCurve{
        List<Node> nodes;
        int generation;

        public List<Node> getNodes() {
            return nodes;
        }

        int direction;
        int targetGeneration;

        public DragonCurve(int startX, int startY, int targetGeneration, int direction){
            this.nodes = new ArrayList<>();
            this.targetGeneration = targetGeneration;
            this.direction = direction;
            addStartAndEndNode(startX,startY);
            startAging();
        }

        private void startAging() {
            while (generation < targetGeneration) {
                int size = nodes.size();

                List<Node> addNodes = new ArrayList<>();
                for(int i=size-1;i>0;i--){
                    Node startNode = nodes.get(i);
                    Node endNode = nodes.get(i-1);
                    int [] vector = getDirection(startNode,endNode);
                    vector = updateWithDirection(vector);
                    if(addNodes.isEmpty()){
                        addNodes.add(new Node(startNode.x+vector[0],startNode.y+vector[1]));
                    }else{
                        Node endPoint = addNodes.get(addNodes.size()-1);
                        addNodes.add(new Node(endPoint.x+vector[0],endPoint.y+vector[1]));
                    }
                }
                generation++;
                this.nodes.addAll(addNodes);
            }
        }


        private int[] updateWithDirection(int[] vector) {
            if(vector[0] == 0 && vector[1] == -1){
                return new int[]{-1,0};
            }else if(vector[0] == 0 && vector[1] == 1){
                return new int[]{1,0};
            }else if(vector[0] == -1 && vector[1] == 0){
                return new int[]{0,1};
            }else{
                return new int[]{0,-1};
            }
        }

        private int[] getDirection(Node startNode, Node endNode) {
            int vectorX = startNode.x - endNode.x;
            int vectorY = startNode.y - endNode.y;

            return new int[]{vectorX,vectorY};
        }

        private void addStartAndEndNode(int startX, int startY) {
            nodes.add(new Node(startX,startY));
            addEndNode(startX,startY);
            this.generation = 0;
        }

        private void addEndNode(int startX,int startY) {
            if(direction == 0){
                nodes.add(new Node(startX+1,startY));
            }else if(direction == 1){
                nodes.add(new Node(startX,startY-1));
            }else if(direction == 2){
                nodes.add(new Node(startX-1,startY));
            }else if(direction == 3){
                nodes.add(new Node(startX,startY+1));
            }
        }
    }

    static int X,Y,D,G;
    static int [][] dragonCurveMap;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dragonCurveMap = new int[101][101];
        List<DragonCurve> dragonCurveList = new ArrayList<>();
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            X = Integer.parseInt(tokens[0]);
            Y = Integer.parseInt(tokens[1]);
            D = Integer.parseInt(tokens[2]);
            G = Integer.parseInt(tokens[3]);

            DragonCurve dragonCurve = new DragonCurve(X,Y,G,D);
            dragonCurveList.add(dragonCurve);
        }

        for (DragonCurve dragonCurve : dragonCurveList) {
            List<Node> nodes = dragonCurve.getNodes();
            for (Node node : nodes) {
                dragonCurveMap[node.x][node.y] = 1;
            }
        }

        int square = 0;
        for (int i = 0; i < 100; i++) { // 맵 크기가 100x100이므로 99까지만 검사
            for (int j = 0; j < 100; j++) {
                if (dragonCurveMap[i][j] == 1 &&       // 왼쪽 위
                        dragonCurveMap[i + 1][j] == 1 &&   // 왼쪽 아래
                        dragonCurveMap[i][j + 1] == 1 &&   // 오른쪽 위
                        dragonCurveMap[i + 1][j + 1] == 1) { // 오른쪽 아래
                    square++;
                }
            }
        }
        bw.write(square+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
