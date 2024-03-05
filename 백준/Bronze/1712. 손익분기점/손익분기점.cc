#include <stdio.h>
int main(void){
    long A;  //고정비용
    long B;  //가변비용
    long C;  //가격
    scanf("%ld",&A);
    scanf("%ld",&B);
    scanf("%ld",&C);
    if(B>=C){
        printf("-1\n");
    }
    else{
        int count=A/(C-B);
        printf("%d\n",count+1);
    }
}