import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int TEST_CASE = 10;
    static class Tree{
        int num;
        Tree left;
        Tree right;

        public Tree(int num){
            this.num = num;
            this.left = null;
            this.right = null;
        }
        public void addNum(int start, int end){
            if(this.num == start){
                if(this.left == null){
                    this.left = new Tree(end);
                }else if(this.right == null){
                    this.right = new Tree(end);
                }
            }else{
                if(this.left != null) this.left.addNum(start,end);
                if(this.right != null) this.right.addNum(start,end);
            }
        }

        public boolean hasPath(int target){
            if(this.num == target) return true;
            boolean leftPath = this.left != null && this.left.hasPath(target);
            boolean rightPath = this.right != null && this.right.hasPath(target);

            return leftPath||rightPath;
        }
    }
    public static void main(String[] args) throws IOException {

        for(int t=1;t<=TEST_CASE;t++){
            String [] tokens = br.readLine().split(" ");
            int testCaseNum = Integer.parseInt(tokens[0]);
            int numOfRoad = Integer.parseInt(tokens[1]);
            tokens = br.readLine().split(" ");
            Tree root = new Tree(0);    //출발지점 Tree 생성

            for(int i=0;i<numOfRoad*2;i+=2){
                int start = Integer.parseInt(tokens[i]);
                int end = Integer.parseInt(tokens[i+1]);
//                System.out.println(start+" "+end);
                root.addNum(start,end);
            }

            boolean result = root.hasPath(99);
            if(result){
                bw.write("#"+testCaseNum+" "+"1\n");
            }else{
                bw.write("#"+testCaseNum+" "+"0\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
