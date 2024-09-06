import java.io.*;

public class Main {
    static class Node{
        String value;
        Node left;
        Node right;
        public Node(String value, Node left, Node right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    static Node head = new Node("A", null, null);

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void insertNode(Node tmp, String root, String left, String right){
        if(tmp.value.equals(root)){
            if(!left.equals(".")){
                tmp.left = new Node(left, null, null);
            }
            if(!right.equals(".")){
                tmp.right = new Node(right, null, null);
            }
        }else{
            if(tmp.left != null){
                insertNode(tmp.left, root, left, right);
            }
            if(tmp.right != null){
                insertNode(tmp.right, root, left, right);
            }
        }
    }
    static void preOrder(Node node) throws IOException{
        if(node == null){
            return;
        }
        bw.write(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }
    static void inOrder(Node node) throws IOException{
        if(node == null){
            return;
        }
        inOrder(node.left);
        bw.write(node.value);
        inOrder(node.right);
    }
    static void postOrder(Node node) throws IOException{
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        bw.write(node.value);
    }
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String [] tokens = br.readLine().split(" ");
            String root = tokens[0];
            String left = tokens[1];
            String right = tokens[2];

            insertNode(head, root, left, right);
        }
        preOrder(head);
        bw.write("\n");
        inOrder(head);
        bw.write("\n");
        postOrder(head);
        bw.write("\n");

        bw.flush();
        br.close();
        bw.close();

    }
}
