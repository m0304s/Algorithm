#include <stdio.h>
int main(void) {
    int N;
    scanf("%d",&N);
    char data[80]={0};
    int n=0;
    int count=0;
    int result=0;
    for(int i=0;i<N;i++){
        result=0;
        n=0;
        count=0;
        scanf("%s",data);
        while(data[n]!=0){
            if(data[n]=='O'){
                count++;
                result=result+count;
            }
            else if(data[n]=='X') {
                count=0;
            }
            n++;
        }
        printf("%d\n",result);
    }
    return 0;
}
