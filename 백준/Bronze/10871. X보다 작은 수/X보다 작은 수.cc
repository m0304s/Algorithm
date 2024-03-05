#include <stdio.h>
int main(void){
    int N,X;
    int array[10000];
    scanf("%d",&N);
    scanf("%d",&X);
    for(int i=0;i<N;i++){
        scanf("%d",&array[i]);
    }
    for(int i=0;i<N;i++){
        if(array[i]<X)
            printf("%d ",array[i]);
    }
}