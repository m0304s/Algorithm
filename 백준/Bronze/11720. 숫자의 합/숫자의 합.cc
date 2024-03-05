#include <stdio.h>
#define MAX 1000
int main(void){
    int count;
    int sum=0;
    char data[MAX];
    scanf("%d",&count);
    scanf("%s",data);
    for(int i=0;i<count;i++){
        sum+=(data[i]-'0');
    }
    printf("%d",sum);
}