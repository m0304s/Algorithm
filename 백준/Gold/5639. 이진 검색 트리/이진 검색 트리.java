import java.io.*;

public class Main {
    static class Tree{
        int value;
        Tree left;
        Tree right;

        public Tree(int value){
            this.value = value;
        }

        public void insert(int newValue){
            if(newValue < this.value){  //좌측 트리에 값이 들어가야함
                if(this.left == null){  //자식 트리가 없을 경우
                    this.left = new Tree(newValue);
                }else{
                    this.left.insert(newValue);
                }
            }else{
                if(this.right == null){
                    this.right = new Tree(newValue);
                }else{
                    this.right.insert(newValue);
                }
            }
        }

        public void postOrder() throws IOException{
            if(this.left != null){
                this.left.postOrder();
            }
            if(this.right != null){
                this.right.postOrder();
            }
            bw.write(this.value + "\n");
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        String input;
        Tree root = null;

        while ((input = br.readLine()) != null) {
            int value = Integer.parseInt(input);

            if (root == null) {
                root = new Tree(value);
            } else {
                root.insert(value);
            }
        }
        if(root != null){
            root.postOrder();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
