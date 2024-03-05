#include <stdio.h>
#include <string.h>
int main(void){
    int cnt[26]={0};
    char data[1000000];
    scanf("%s",data);
    int len=0;
    int num=0;
    len=strlen(data);
    for(int i=0;i<len;i++){
        if(data[i]>='a'&&data[i]<='z'){
            num=data[i]-'a';
        }
        else if(data[i]>='A'&&data[i]<='Z')
            num=data[i]-'A';
        cnt[num]++;
    }
    int max;
    max=cnt[0];
    int result=0;
    int select=0;
    for(int i=1;i<26;i++){
        if(max<cnt[i]){
            max=cnt[i];
            select=i;
        }
    }
    for(int i=0;i<26;i++){
        if(max==cnt[i])
            result++;
    }
    if(result>1){
        printf("?\n");
    }
    else{
        printf("%c\n",select+'A');
    }
}