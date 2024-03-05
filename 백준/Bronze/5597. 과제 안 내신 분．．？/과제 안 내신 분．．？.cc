#include <stdio.h>
int main(void){
    int array[30]={0};
    for(int i=0;i<28;i++){
        int N;
        scanf("%d",&N);
        array[N-1]=1;
    }
    for(int i=0;i<30;i++){
        if(array[i]!=1)
            printf("%d\n",i+1);
    }
}