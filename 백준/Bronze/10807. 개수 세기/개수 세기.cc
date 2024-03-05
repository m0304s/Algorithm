#include <stdio.h>
int main(void){
    int N;
    int array[100];
    scanf("%d",&N);
    for(int i=0;i<N;i++){
        scanf("%d",&array[i]);
    }
    int find=0;
    scanf("%d",&find);
    int count=0;
    for(int i=0;i<N;i++){
        if(array[i]==find){
            count++;
        }
    }
    printf("%d",count);
}