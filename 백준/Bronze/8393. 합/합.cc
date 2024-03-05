#include <stdio.h>
int main(void)
{
    int n,sum;
    scanf("%d",&n);
    sum=0;
    for(int i=1;i<=n;i++)
    {
        sum=sum+i;
    }
    printf("%d\n",sum);
}