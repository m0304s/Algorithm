#include <stdio.h>
#include <string.h>
#define MAX 100
int main(void){
    char data[MAX];
    int index[MAX];
    scanf("%s",data);
    for(int i=0;i<strlen(data);i++){
        index[i]=int(data[i]);
    }
    int result=0;
    int n=97;
    for(int i=0;i<26;i++){
        for(int j=0;j<strlen(data);j++){
            if(n==index[j]) {
                result=j;
                break;
            }
            else
                result=-1;
        }
        printf("%d ",result);
        n++;
    }
}