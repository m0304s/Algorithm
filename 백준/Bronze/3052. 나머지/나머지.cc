#include <stdio.h>
int main(void){
    int array[10]={0};
    int N;
    for(int i=0;i<10;i++){
        scanf("%d",&N);
        array[i]=N%42;
    }
    int count=0;
    for(int i=0;i<10;i++){
        int result=0;
        for(int j=i+1;j<10;j++){
            if(array[i]==array[j])
                result++;
        }
        if(result==0)
            count++;
    }
    printf("%d\n",count);
}