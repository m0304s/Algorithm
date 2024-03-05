#include <stdio.h>
int main(void){
    int n,i;
    scanf("%d",&n);
    for(i=1;i<n;i++)
        n=n-i;
    if(i%2==0){
        printf("%d/%d",n,i+1-n);
    }
    else
        printf("%d/%d",i+1-n,n);
}