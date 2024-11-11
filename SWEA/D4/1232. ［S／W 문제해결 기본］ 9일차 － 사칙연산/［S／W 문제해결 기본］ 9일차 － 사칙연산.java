import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Tree{
        int treeNum;
        String value;
        Tree left;
        Tree right;

        public Tree(int treeNum,String value,int leftTreeNum, int rightTreeNum){
            this.treeNum = treeNum;
            this.value = value;
            this.left = new Tree(leftTreeNum);
            this.right = new Tree(rightTreeNum);
        }

        public Tree(int treeNum,String value,int leftTreeNum){
            this.treeNum = treeNum;
            this.value = value;
            this.left = new Tree(leftTreeNum);
            this.right = null;
        }

        public Tree(int treeNum){
            this.treeNum = treeNum;
            this.value = null;
            this.left = null;
            this.right = null;
        }

        public void add(int treeNum, String value, int leftTreeNum, int rightTreeNum) {
            if(this.treeNum == treeNum){
                this.value = value;
                if(this.left == null) this.left = new Tree(leftTreeNum);
                if(this.right == null) this.right = new Tree(rightTreeNum);
                return;
            }
            if(this.left != null) this.left.add(treeNum,value,leftTreeNum,rightTreeNum);
            if(this.right != null) this.right.add(treeNum,value,leftTreeNum,rightTreeNum);
        }

        public void addNum(int treeNum, int value) {
            if(this.treeNum == treeNum){
                this.value = String.valueOf(value);
                return;
            }

            if(this.left != null) this.left.addNum(treeNum,value);
            if(this.right != null) this.right.addNum(treeNum,value);
        }

        public int calculate(){
            if(isNumeric(this.value)){
                return Integer.parseInt(this.value);
            }

            int leftValue = left.calculate();
            int rightValue = right.calculate();

            switch (value){
                case "+" : return leftValue+rightValue;
                case "-" : return leftValue - rightValue;
                case "*" : return leftValue*rightValue;
                case "/" : return leftValue/rightValue;
                default: throw new IllegalArgumentException();
            }
        }
        private boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static Tree root;
    static String formula;
    static int result;
    public static void main(String[] args) throws IOException {
        int T = 10;
        for(int t=1;t<=T;t++){
            root = null;
            int N = Integer.parseInt(br.readLine());
            result = 0;
            formula = "";
            for(int i=0;i<N;i++){
                String [] tokens = br.readLine().split(" ");
                if(tokens.length == 4){
                    // treeNum, value, leftTreeNum, rightTreeNum 형태
                    int treeNum = Integer.parseInt(tokens[0]);
                    String value = tokens[1];
                    int leftTreeNum = Integer.parseInt(tokens[2]);
                    int rightTreeNum = Integer.parseInt(tokens[3]);

                    if(root == null){
                        root = new Tree(treeNum,value,leftTreeNum,rightTreeNum);
                    }else{
                        root.add(treeNum,value,leftTreeNum,rightTreeNum);
                    }
                }else if(tokens.length == 2){
                    // treeNum, value 형태
                    int treeNum = Integer.parseInt(tokens[0]);
                    int value = Integer.parseInt(tokens[1]);
                    root.addNum(treeNum,value);
                }
            }
            int result = root.calculate();
            System.out.println(result);
            bw.write("#"+t+" "+result+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
