#include <stdio.h>
int main(void){
    int A,B,V;
    scanf("%d",&A);
    scanf("%d",&B);
    scanf("%d",&V);
    int height_of_day= A-B;
    int day=0;
    if(A>=V)
        day=1;
    else{
        if((V-A)%height_of_day==0)
            day=(V-A)/height_of_day+1;
        else
            day=(V-A)/height_of_day+2;
    }
    printf("%d",day);
}