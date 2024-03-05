#include <stdio.h>
int main(void){
    int array[9];
    for(int i=0;i<9;i++)
        scanf("%d",&array[i]);
    int MAX=array[0];
    int index=0;
    for(int i=0;i<9;i++){
        if(array[i]>MAX){
            MAX=array[i];
            index=i;
        }
    }
    printf("%d\n%d\n",MAX,index+1);
}