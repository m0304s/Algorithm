#include <stdio.h>
#include <string.h>
int main(void){
    char data[15]={0};
    int time[10]={0};
    int n=2;
    time[0]=11;
    for(int i=1;i<10;i++){
        time[i]=n++;
    }
    scanf("%s",data);
    int len;
    len=strlen(data);
    int count=0;
    for(int i=0;i<len;i++) {
        int result=0;
        if (data[i] >= 'A' && data[i] <= 'C') {
            result=time[2];
        }
        else if (data[i] >= 'D' && data[i] <= 'F') {
            result=time[3];
        }
        else if (data[i] >= 'G' && data[i] <= 'I') {
            result=time[4];
        }
        else if (data[i] >= 'J' && data[i] <= 'L') {
            result=time[5];
        }
        else if (data[i] >= 'M' && data[i] <= 'O') {
            result=time[6];
        }
        else if (data[i] >= 'P' && data[i] <= 'S') {
            result=time[7];
        }
        else if (data[i] >= 'T' && data[i] <= 'V') {
            result=time[8];
        }
        else if (data[i] >= 'W' && data[i] <= 'Z') {
            result=time[9];
        }
        count=count+result;
    }
    printf("%d\n",count);
}