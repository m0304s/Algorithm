import java.util.*;

class Solution {
    public static int cnt;
    public static int answer[][];
    public int [][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        ArrayList<Node> list = new ArrayList<>();
        for(int i=0;i<nodeinfo.length;i++){
            list.add(new Node(i+1,nodeinfo[i][0],nodeinfo[i][1],null,null));
        }
        Collections.sort(list);
        
        Node root = list.get(0);
        for(int i=1;i<nodeinfo.length;i++){
            insertNode(root,list.get(i));
        }
        cnt=0;
        preOrder(root);
        cnt=0;
        postOrder(root);
        return answer;
    }
    void insertNode(Node parent, Node child){
        //부모의 X 좌표보다 자식의 X 좌표가 작다 -> left 설정
        if(parent.getX()>child.getX()){
            if(parent.getLeft()==null){
                parent.setLeft(child);
            }else{
                insertNode(parent.getLeft(),child);
            }
        }else{
            if(parent.getRight()==null){
                parent.setRight(child);
            }else{
                insertNode(parent.getRight(),child);
            }
        }
    }
    void preOrder(Node root){
        if(root!=null){
            answer[0][cnt++]=root.getNum();
            preOrder(root.getLeft());
            preOrder(root.getRight());   
        }
    }    
    void postOrder(Node root){
        if(root!=null){
            postOrder(root.getLeft());
            postOrder(root.getRight());   
            answer[1][cnt++]=root.getNum();
        }
    }
}
class Node implements Comparable<Node>{
    private int num;
    private int x;
    private int y;
    private Node left;
    private Node right;
    
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getNum(){
        return this.num;
    }
    public Node getLeft(){
        return this.left;
    }
    public Node getRight(){
        return this.right;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public void setNum(int num){
        this.num=num;
    }
    public void setLeft(Node node){
        this.left = node;
    }
    public void setRight(Node node){
        this.right=node;
    }
    public Node(int num,int x,int y,Node left, Node right){
        this.num =num;
        this.x= x;
        this.y= y;
        this.left = left;
        this.right = right;
    }
    public int compareTo(Node o1){
        if(this.y==o1.y){
            //같은 레벨 -> x좌표 작은순으로
            return this.x-o1.x;
        }else{
            return o1.y-this.y;
        }
    }
}