#include <stdio.h>
int han_num(int N){
    int count=0;
    for(int i=1;i<=N;i++){
        if(i<100)
            count++;
        else if(i>=100&&i<=1000){
            int hund = i/100;
            int ten = (i%100)/10;
            int one = (i%100)%10;
            if((hund-ten)==(ten-one))
                count++;
        }
    }
    return count;
}
int main(void){
    int N;
    scanf("%d",&N);
    int result=0;
    result=han_num(N);
    printf("%d\n",result);
}