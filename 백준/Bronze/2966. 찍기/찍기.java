import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String [] Adrian = {"A","B","C"};
        String [] Bruno = {"B","A","B","C"};
        String [] Goran = {"C","C","A","A","B","B"};
        kb.nextLine();
        String answer = kb.nextLine();
        
        int AdrianC = 0;
        int BrunoC = 0;
        int GoranC = 0;

        int count = 0;
        for(int i=0;i<answer.length();i++){
            if(count==Adrian.length){
                count=0;
            }
            String answer_index = Character.toString(answer.charAt(i));
            if(Adrian[count].equals(answer_index)){
                AdrianC++;
            }
            count++;
        }

        count=0;
        for(int i=0;i<answer.length();i++){
            if(count==Bruno.length)
                count=0;
            String answer_index = Character.toString(answer.charAt(i));
            if(Bruno[count].equals(answer_index)){
                BrunoC++;
            }
            count++;
        }
        count=0;
        for(int i=0;i<answer.length();i++){
            if(count==Goran.length)
                count=0;
            String answer_index = Character.toString(answer.charAt(i));
            if(Goran[count].equals(answer_index)){
                GoranC++;
            }
            count++;
        }

        int max = Integer.max(GoranC, BrunoC);
        if(max <AdrianC){
            max = AdrianC;
        }

        System.out.println(max);
        if(max==AdrianC){
            System.out.println("Adrian");
        }
        if(max==BrunoC){
            System.out.println("Bruno");
        }
        if(max==GoranC){
            System.out.println("Goran");
        }

        kb.close();

    }
}