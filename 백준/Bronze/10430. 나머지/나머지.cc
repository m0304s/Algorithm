#include <stdio.h>
int main(void)
{
    int x,y,z;
    scanf("%d %d %d",&x,&y,&z);
    printf("%d\n",(x+y)%z);
    printf("%d\n",((x%z) + (y%z))%z);
    printf("%d\n",(x*y)%z);
    printf("%d\n",((x%z)*(y%z))%z);
    return 0;
}

