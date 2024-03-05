#include <stdio.h>
int main(void)
{
    int x; //영수증에 적힌 총 금액 X
    int n; //영수증에 적힌 구매한 물건의 종류의 수
    int a;
    int b;
    int price=0;
    scanf("%d",&x);
    scanf("%d",&n);
    for(int i=0;i<n;i++)
    {
        scanf("%d %d",&a,&b);
        price=price+a*b;
    }
    if(price==x)
    printf("Yes\n");
    else
    printf("No\n");
}