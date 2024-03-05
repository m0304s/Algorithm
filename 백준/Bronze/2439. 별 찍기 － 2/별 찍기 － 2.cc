#include <stdio.h>
int main(void){
    int count;
    scanf("%d",&count);
    for(int x=1;x<=count;x++){
        for(int j=0;j<count-x;j++)
            printf(" ");
        for(int i=0;i<x;i++)
            printf("*");
        printf("\n");
    }
}