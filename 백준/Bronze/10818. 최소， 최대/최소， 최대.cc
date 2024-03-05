#include <stdio.h>
int main(void){
    int N;
    scanf("%d",&N);
    int array[1000000];
    for(int i=0;i<N;i++){
        scanf("%d",&array[i]);
    }
    int MAX=array[0];
    int MIN=array[0];
    for(int i=0;i<N;i++){
        if(array[i]>MAX)
            MAX=array[i];
        if(array[i]<MIN)
            MIN=array[i];
    }
    printf("%d %d\n",MIN,MAX);
}