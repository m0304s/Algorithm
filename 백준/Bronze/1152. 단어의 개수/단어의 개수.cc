#include <stdio.h>
#include <string.h>
int main(void){
    char str[1000000];
    scanf("%[^\n]s",str);
    int len;
    len=strlen(str);
    int count=0;
    if(len==1){
        if(str[0]==' '){
            printf("0\n");
            return 0;
        }
    }
    for(int i=1;i<len-1;i++)
        if(str[i]==' ')
            count++;
    printf("%d\n",count+1);
}