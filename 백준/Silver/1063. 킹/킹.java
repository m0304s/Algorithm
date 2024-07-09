import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Node{
        int x;
        int y;

        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
            char cx = returntoChar(x);

            return Character.toString(cx) + y;
        }
    }
    
    static Node[] array = new Node[2];

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        char kingS = tokens[0].charAt(0);
        char kingNum = tokens[0].charAt(1);

        int kingX = calcIndex(kingS)-64;
        int kingY = calcIndex(kingNum)-48;

        Node king = new Node(kingX, kingY);
        array[0] = king;

        char stoneCX = tokens[1].charAt(0);
        char stoneCY = tokens[1].charAt(1);

        int stoneX = calcIndex(stoneCX)-64;
        int stoneY = calcIndex(stoneCY)-48;

        Node stone = new Node(stoneX, stoneY);
        array[1] = stone;

        int count = Integer.parseInt(tokens[2]);
        for(int i=0;i<count;i++){
            String dir = br.readLine(); //이동 방향
            simulation(dir);
        }
        bw.write(array[0].toString()+"\n");
        bw.write(array[1].toString()+"\n");
        bw.flush();
    }
    public static int calcIndex(char input){
        return (int)input;
    }
    public static char returntoChar(int index){
        index = index + 64;
        return (char)index;
    }
    //array[0] = king
    //array[1] = stone

    public static void simulation(String dir){
        int dx = 0;
        int dy = 0;

        switch (dir) {
            case "R":
                dx++;
                break;
            case "L":
                dx--;
                break;
            case "B":
                dy--;
                break;
            case "T":
                dy++;
                break;
            case "RT":
                dx++;
                dy++;
                break;
            case "LT":
                dx--;
                dy++;
                break;
            case "RB":
                dx++;
                dy--;
                break;
            case "LB":
                dx--;
                dy--;
                break;            
            default:
                break;
        }

        int newKingX = array[0].x + dx;
        int newKingY = array[0].y + dy;;

        int newStoneX = array[1].x + dx;
        int newStoneY = array[1].y + dy;

        //같은 위치에 있을 경우 킹과 돌의 위치를 변경
        if(checkSamePos(newKingX,newKingY)){
            boolean kingmovable = checkMovable(newKingX, newKingY);
            boolean stonemovable = checkMovable(newStoneX, newStoneY);

            if(kingmovable && stonemovable){
                array[0].x = newKingX;
                array[0].y = newKingY;
                array[1].x = newStoneX;
                array[1].y = newStoneY;
            }
        }else{
        //같은 위치에 있지 않는 경우 킹만 위치 변경
            boolean kingmovable = checkMovable(newKingX, newKingY);
            if(kingmovable){
                array[0].x = newKingX;
                array[0].y = newKingY;
            }
        }
    }
    public static Boolean checkSamePos(int newX , int newY){
        if(newX == array[1].x && newY == array[1].y){
            return true;
        }
        return false;
    }
    public static Boolean checkMovable(int x,int y){
        if(x<1 || x > 8 || y < 1 || y > 8){
            return false;
        }else
            return true;
    }
}