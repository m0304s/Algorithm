#include <stdio.h>
#define MAX 10000
int self_number(int n){
    int sum=n;
    while(n!=0){
        sum+=n%10;
        n=n/10;
    }
    return sum;
}
int main(void){
    int array[MAX+1];
    for(int i=1;i<=MAX;i++){
        int check=self_number(i);
        if(check<10001)
            array[check]=1;
    }
    for(int i=1;i<=10000;i++)
        if(array[i]!=1)
            printf("%d\n",i);
    return 0;
}