import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int TEST_CASE = 10;
    static int N;

    static class Tree{
        int num;
        String word;
        Tree left;
        Tree right;

        public Tree(int num,String word,int left, int right){
            this.num = num;
            this.word = word;
            this.left = new Tree(left,null);
            this.right = new Tree(right,null);
        }
        public Tree(int num,String word){
            this.num = num;
            this.word = word;
            this.left = null;
            this.right = null;
        }
        public Tree(int num,String word,int left){
            this.num = num;
            this.word = word;
            this.left = new Tree(left,null);
            this.right = null;
        }
        public void insertNodeWithoutChild(int num,String word){
            if(this.num == num){
                if(this.word == null){
                    this.word = word;
                }
            }else{
                if(this.left != null) this.left.insertNodeWithoutChild(num,word);
                if(this.right != null) this.right.insertNodeWithoutChild(num,word);
            }
        }
        public void insertNodeWithTwoChild(int num,String word,int left,int right){
            if(this.num == num){
                if(this.word == null){
                    this.word = word;
                    this.left = new Tree(left,null);
                    this.right = new Tree(right,null);
                }
            }else{
                if(this.left!=null) this.left.insertNodeWithTwoChild(num,word,left,right);
                if(this.right != null) this.right.insertNodeWithTwoChild(num,word,left,right);
            }
        }
        public void insertNodeWithOneChild(int num,String word,int left){
            if(this.num == num){
                if(this.word == null){
                    this.word = word;
                    this.left = new Tree(left,null);
                }
            }else{
                if(this.left != null) this.left.insertNodeWithOneChild(num,word,left);
                if(this.right != null) this.right.insertNodeWithOneChild(num,word,left);
            }
        }

        public void inOrderTraversal() throws IOException{
            if(this.left != null) this.left.inOrderTraversal();
            if(this.word != null) bw.write(this.word);
            if(this.right != null) this.right.inOrderTraversal();
        }
    }

    public static void main(String[] args) throws IOException {
        for(int t=1;t<=TEST_CASE;t++){
            N = Integer.parseInt(br.readLine());
            Tree root = null;
            for(int i=0;i<N;i++){
                String [] tokens = br.readLine().split(" ");
                if(root == null){
                    //자식이 있는 경우
                    if(tokens.length == 2){
                        root = new Tree(Integer.parseInt(tokens[0]),tokens[1]);
                    }else if(tokens.length == 3){
                        root = new Tree(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]));
                    }else if(tokens.length == 4){
                        root = new Tree(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));
                    }
                }else{
                    //데이터가 기존에 있는 경우
                    if(tokens.length == 2){
                        root.insertNodeWithoutChild(Integer.parseInt(tokens[0]),tokens[1]);
                    }else if(tokens.length == 3){
                        root.insertNodeWithOneChild(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]));
                    }else if(tokens.length == 4){
                        root.insertNodeWithTwoChild(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));
                    }
                }
            }
            bw.write("#" + t + " ");
            if (root != null) {
                root.inOrderTraversal();
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
