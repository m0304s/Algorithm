#include <stdio.h>
#include <string.h>
#define MAX 100
int main(void){
    int T;
    scanf("%d",&T);
    for(int i=0;i<T;i++){
        int count=0;
        char data[MAX];
        scanf("%d %s",&count,data);
        int n=0;
        for(int x=0;x<strlen(data);x++){
            for(int j=0;j<count;j++) {
                printf("%c", data[n]);
            }
            n++;
        }
        printf("\n");
    }
}