#include <stdio.h>
int main(void){
    int N;
    scanf("%d",&N);
    int original_number=N;
    int count=0;
    int new_num=N;
    while(1){
        int sum=0;
        sum=new_num/10+new_num%10;
        new_num=10*(new_num%10)+(sum%10);
        count++;
        if(new_num==N)
            break;
    }
    printf("%d\n",count);
}