#include <stdio.h>
#include <string.h>
int main(void){
    char data[100];
    scanf("%s",data);
    int len;
    len=strlen(data);
    int count=0;
    for(int i=0;i<len;i++){
        if(data[i]=='c'&&data[i+1]=='='){
                count++;
                i=i+1;
        }
        else if(data[i]=='c'&&data[i+1]=='-'){
                count++;
                i=i+1;
        }
        else if(data[i]=='d'&&data[i+1]=='z'&&data[i+2]=='=') {
            count++;
            i = i + 2;
        }
        else if(data[i]=='d'&&data[i+1]=='-'){
                count++;
                i=i+1;
        }
        else if(data[i]=='l'&&data[i+1]=='j'){
            count++;
            i=i+1;
        }
        else if(data[i]=='n'&&data[i+1]=='j'){
            count++;
            i=i+1;
        }
        else if(data[i]=='s'&&data[i+1]=='='){
            count++;
            i=i+1;
        }
        else if(data[i]=='z'&&data[i+1]=='=') {
            count++;
            i=i+1; }
        else
            count++;
    }
    printf("%d\n",count);
}